package com.publicis.sapient.weatherpridictionapi.model;

import lombok.Data;

@Data
public class City {

    /** id we get in api response */
    private float id;

    /** name we get in api response */
    private String name;

    /** coord we get in api response*/
    private Coord coord;

    /**  country we get in api response */
    private String country;

    /**  we get in api response */
    private long population;

    /**  we get in api response */
    private float timezone;

    /**  we get in api response */
    private float sunrise;

    /**  we get in api response */
    private float sunset;
}
