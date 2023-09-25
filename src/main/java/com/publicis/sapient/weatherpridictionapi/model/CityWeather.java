package com.publicis.sapient.weatherpridictionapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CityWeather {
    /** cod. */
    private String cod;

    /** message. */
    private float message;

    /** cnt we get in api response */
    private float cnt;

    /** list we get in api response */
    private List<WeatherObjectDetailsList> list = new ArrayList<>();

    /** City object we get in api response */
    private City CityObject;
}
