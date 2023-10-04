package com.publicis.sapient.weatherpridictionapi.service;

import com.publicis.sapient.weatherpridictionapi.dto.WeatherPredictDto;

import java.util.List;

public interface WeatherPredictService {

    WeatherPredictDto getCityWeather(String city);
}
