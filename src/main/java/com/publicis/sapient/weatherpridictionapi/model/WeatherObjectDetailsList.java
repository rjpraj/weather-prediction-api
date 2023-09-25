package com.publicis.sapient.weatherpridictionapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WeatherObjectDetailsList {
    /** The dt. */
    private long dt;

    /** The main. */
    private WeatherMainDetails main;

    /** The weather. */
    private List<CityWeatherDetails> weather = new ArrayList<>();

    /** The clouds. */
    private Clouds clouds;

    /** The wind. */
    private Wind wind;

    /** received from api response  */
    private float visibility;

    /** received from api response  */
    private float pop;

    /** The sys. */
    private Sys sys;

    /** The dt txt. */
    private String dt_txt;

}
