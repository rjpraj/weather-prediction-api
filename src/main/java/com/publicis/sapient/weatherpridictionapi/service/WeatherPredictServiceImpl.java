package com.publicis.sapient.weatherpridictionapi.service;

import com.publicis.sapient.weatherpridictionapi.client.OpenWeatherClient;
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
    public List<WeatherPredictDto> getCityWeather(String city) {
        LOGGER.trace("Entering getCityWeather(city={})", city);

        CityWeather cityWeather = weatherClient.getWeather(city);

        List<WeatherPredictDto> cityWeatherList = new ArrayList<>();

        List<WeatherObjectDetailsList> threeDaysCityWeather = cityWeather.getList().subList(0, 24);
        // count number of days for which data is collected
        int dayCount = 0;
        // keep date tp match
        String dateMatch="";
        for (WeatherObjectDetailsList weatherObjectList : threeDaysCityWeather) {

            String date_txt = weatherObjectList.getDt_txt().split("\\s+")[0];
            if(!date_txt.equals(dateMatch)){
                dayCount++;
                dateMatch=date_txt;
                LOGGER.info("new date found count "+dayCount);
            }
            if(dayCount>3){
                LOGGER.info("3 days data collected, skip loop now "+dayCount);
                break;
            }
            WeatherPredictDto weatherPredictionDto = new WeatherPredictDto();

            if (weatherObjectList.getMain() != null) {

                weatherPredictionDto.setHigh(weatherObjectList.getMain().getTemp_max());
                weatherPredictionDto.setLow(weatherObjectList.getMain().getTemp_min());
                weatherPredictionDto.setDate(weatherObjectList.getDt_txt());

                float temperatureInCelsius = WeatherUtil.convertTempToCelsius(
                        weatherObjectList.getMain().getTemp());

                if (temperatureInCelsius > 40.0) {
                    weatherPredictionDto.setMessage(Constants.USE_SUNSCREEN_LOTION);
                } else if (weatherObjectList.getWeather().get(0).getMain().contains(Constants.RAIN)) {
                    weatherPredictionDto.setMessage(Constants.CARRY_UMBRELLA);
                } else if(weatherObjectList.getWind().getSpeed() > 10.0){
                    weatherPredictionDto.setMessage(Constants.CARRY_UMBRELLA);
                } else if(weatherObjectList.getWeather().get(0).getMain().contains(Constants.THUNDERSTORM)){
                    weatherPredictionDto.setMessage(Constants.THUNDERSTORM_MESSAGE);
                }
                else {
                    weatherPredictionDto.setMessage(weatherObjectList.getWeather().get(0).getMain());
                }

            } else {
                LOGGER.error("Temperature data is missing for the city");
                throw new RuntimeException(Constants.EXTERNAL_API_SENT_MALFORMED_DATA);
            }

            cityWeatherList.add(weatherPredictionDto);
        }

        return cityWeatherList;
    }
}
