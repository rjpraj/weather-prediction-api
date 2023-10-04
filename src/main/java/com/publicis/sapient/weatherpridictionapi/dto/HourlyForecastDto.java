package com.publicis.sapient.weatherpridictionapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HourlyForecastDto {
    private String time;
    private int temp;
    private String message;
}
