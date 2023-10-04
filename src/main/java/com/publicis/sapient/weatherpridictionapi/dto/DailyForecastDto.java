package com.publicis.sapient.weatherpridictionapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class DailyForecastDto {

    private String date;

    /** highest temperature of the date*/
    private int high;

    /** lowest temperature of the date*/
    private int low;
}
