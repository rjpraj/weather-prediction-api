package com.publicis.sapient.weatherpridictionapi.dto;

import lombok.Data;

@Data
public class WeatherPredictDto {
    /** highest temperature */
    private float high;

    /** lowest temperature */
    private float low;

    /** message based on temperature */
    private String message;

    /** Date + time window of temperature prediction */
    private String date;
}
