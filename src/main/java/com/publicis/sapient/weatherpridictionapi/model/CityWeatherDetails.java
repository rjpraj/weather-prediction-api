package com.publicis.sapient.weatherpridictionapi.model;

import lombok.Data;

@Data
public class CityWeatherDetails {
    /** The id. */
    private float id;

    /** The main. */
    private String main;

    /** The description. */
    private String description;

    /** The icon. */
    private String icon;

}
