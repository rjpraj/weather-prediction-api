package com.publicis.sapient.weatherpridictionapi.controller;

import com.publicis.sapient.weatherpridictionapi.dto.WeatherPredictDto;
import com.publicis.sapient.weatherpridictionapi.service.WeatherPredictService;
import com.publicis.sapient.weatherpridictionapi.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/weather")
public class WeatherPredictController {
    @Autowired
    WeatherPredictService weatherPredictService;

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherPredictController.class);

    /**
     * end-point to get weather forecast of a city for next three days.
     *
     * This end-point retrieves the weather forecast by {city} from OpenWeatherMap
     * Api and then checks for city temperature and weather condition.
     *
     * @param city the city for which weather forecast is needed.
     * @return the list of WeatherPrediction
     */
    @GetMapping("/{city}")
    public ResponseEntity<?> getCityWeatherPrediction(@PathVariable String city) {
        LOGGER.trace("Entering getCityWeatherPrediction(city={})", city);

        if (city == null || city.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            LOGGER.info("I am called : Controller for city :"+city);
            return new ResponseEntity<WeatherPredictDto>(
                    weatherPredictService.getCityWeather(city), HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("error found",ex.getStackTrace());
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
