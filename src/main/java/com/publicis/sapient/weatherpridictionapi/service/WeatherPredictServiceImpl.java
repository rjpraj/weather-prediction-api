package com.publicis.sapient.weatherpridictionapi.service;

import com.publicis.sapient.weatherpridictionapi.client.OpenWeatherClient;
import com.publicis.sapient.weatherpridictionapi.dto.DailyForecastDto;
import com.publicis.sapient.weatherpridictionapi.dto.HourlyForecastDto;
import com.publicis.sapient.weatherpridictionapi.dto.WeatherPredictDto;
import com.publicis.sapient.weatherpridictionapi.model.CityWeather;
import com.publicis.sapient.weatherpridictionapi.model.WeatherObjectDetailsList;
import com.publicis.sapient.weatherpridictionapi.model.WeatherPrediction;
import com.publicis.sapient.weatherpridictionapi.utils.Constants;
import com.publicis.sapient.weatherpridictionapi.utils.DateConverstionUtil;
import com.publicis.sapient.weatherpridictionapi.utils.WeatherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherPredictServiceImpl implements WeatherPredictService{
    @Autowired
    OpenWeatherClient weatherClient;
    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherPredictServiceImpl.class);
    @Override
    public WeatherPredictDto getCityWeather(String city) {
        LOGGER.trace("Entering getCityWeather(city={})", city);
        CityWeather cityWeather;
//        calling weather API
        try{
            cityWeather = weatherClient.getWeather(city);
        }
        catch (Exception e){
            if(e.getMessage().contains("city not found")){
                throw new RuntimeException(Constants.WEATHER_NOT_FOUND);
            }
            throw new RuntimeException(Constants.API_DOWN);
        }

        List<WeatherObjectDetailsList> threeDaysCityWeather = cityWeather.getList().subList(0, 24);

//        output dto declartion
        WeatherPredictDto cityWeatherDto = new WeatherPredictDto();

        // count number of days for which data is collected
        int dayCount = 0;
        // keep date tp match
        String dateMatch="";
        int maxTemp = Integer.MIN_VALUE;
        int minTemp = Integer.MAX_VALUE;
        List<HourlyForecastDto> hourlyForecastDtoList = new ArrayList<>();
        List<DailyForecastDto> dailyForecastDtoList = new ArrayList<>();
        for (WeatherObjectDetailsList weatherObjectList : threeDaysCityWeather) {

            String date_txt = weatherObjectList.getDt_txt().split("\\s+")[0];
            if(!date_txt.equals(dateMatch)){
                if(dayCount>0){
//                    set data min max and date data in output dto
                    DailyForecastDto dailyForecastDto = new DailyForecastDto();
                    dailyForecastDto.setDate(dateMatch);
                    dailyForecastDto.setHigh(maxTemp);
                    dailyForecastDto.setLow(minTemp);
                    dailyForecastDtoList.add(dailyForecastDto);
                }
                dayCount++;
                dateMatch=date_txt;
//                LOGGER.info("new date found count "+dayCount);
                maxTemp = Integer.MIN_VALUE;
                minTemp = Integer.MAX_VALUE;
            }
            if(dayCount>3){
//                LOGGER.info("3 days data collected, skip loop now "+dayCount);
                break;
            }
            WeatherPredictDto weatherPredictionDto = new WeatherPredictDto();

            if (weatherObjectList.getMain() != null) {
//                daily min max temp calculation | for daily forecast list
                float maxInCelsius = WeatherUtil.convertTempToCelsius(weatherObjectList.getMain().getTemp_max());
                int hourlyMaxTemp = Math.round(maxInCelsius); // convert temp to 2 decimal place
                float minInCelsius = WeatherUtil.convertTempToCelsius(weatherObjectList.getMain().getTemp_min());
                int hourlyMinTemp = Math.round(minInCelsius);
                minTemp = Math.min(minTemp,hourlyMinTemp);
                maxTemp = Math.max(maxTemp,hourlyMaxTemp);

                if(dayCount==1) {
                    HourlyForecastDto hourlyForecastDto = new HourlyForecastDto();
                    float temperatureInCelsius = WeatherUtil.convertTempToCelsius(
                            weatherObjectList.getMain().getTemp());
                    int hourlyTemp = Math.round(temperatureInCelsius);
                    hourlyForecastDto.setTemp(hourlyTemp);

//                    fetch hourly time window
                    String hourlyTime = weatherObjectList.getDt_txt().split("\\s+")[1].substring(0,5);
                    hourlyForecastDto.setTime(hourlyTime);

                    if (temperatureInCelsius > 40.0) {
                        hourlyForecastDto.setMessage(Constants.USE_SUNSCREEN_LOTION);
                    } else if (weatherObjectList.getWeather().get(0).getMain().contains(Constants.RAIN)) {
                        hourlyForecastDto.setMessage(Constants.CARRY_UMBRELLA);
                    } else if (weatherObjectList.getWind().getSpeed() > 10.0) {
                        hourlyForecastDto.setMessage(Constants.TOO_WINDY);
                    } else if (weatherObjectList.getWeather().get(0).getMain().contains(Constants.THUNDERSTORM)) {
                        hourlyForecastDto.setMessage(Constants.THUNDERSTORM_MESSAGE);
                    } else {
                        hourlyForecastDto.setMessage(weatherObjectList.getWeather().get(0).getMain());
                    }
                    hourlyForecastDtoList.add(hourlyForecastDto);
                }

            } else {
                LOGGER.error("Temperature data is missing for the city");
                throw new RuntimeException(Constants.EXTERNAL_API_SENT_MALFORMED_DATA);
            }

        }

        cityWeatherDto.setHourlyForecastList(hourlyForecastDtoList);
        cityWeatherDto.setDailyForecastList(dailyForecastDtoList);

        return cityWeatherDto;
    }
}
