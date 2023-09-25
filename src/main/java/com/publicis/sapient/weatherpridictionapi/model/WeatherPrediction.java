package com.publicis.sapient.weatherpridictionapi.model;

import lombok.Data;

@Data
public class WeatherPrediction {
    /** The high. */
    private float high;

    /** The low. */
    private float low;

    /** The message. */
    private String message;
}
