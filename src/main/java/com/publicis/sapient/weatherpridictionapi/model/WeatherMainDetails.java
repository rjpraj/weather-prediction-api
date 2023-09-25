package com.publicis.sapient.weatherpridictionapi.model;

import lombok.Data;

@Data
public class WeatherMainDetails {
    /** The temp. */
    private float temp;

    /** The temp min. */
    private float temp_min;

    /** The temp max. */
    private float temp_max;

    /** The pressure. */
    private float pressure;

    /** The sea level. */
    private float sea_level;

    /** The grnd level. */
    private float grnd_level;

    /** The humidity. */
    private float humidity;

    /** The temp kf. */
    private float temp_kf;

}
