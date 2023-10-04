package com.publicis.sapient.weatherpridictionapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WeatherPredictDto {

    /** Contains daily min max temp forecast for 3 days */
    private List<DailyForecastDto> dailyForecastList = new ArrayList<>();

    /** Contains todays forecast for every 3 hours with message */
    private List<HourlyForecastDto> hourlyForecastList = new ArrayList<>();
}
