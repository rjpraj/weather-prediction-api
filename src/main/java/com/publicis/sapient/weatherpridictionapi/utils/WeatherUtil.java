package com.publicis.sapient.weatherpridictionapi.utils;

import java.text.DecimalFormat;

public class WeatherUtil {
    /**
     * Converts temp to celsius.
     *
     * @param temp the temp
     * @return the float
     */
    public static float convertTempToCelsius(float temp) {
        return temp - 273.15F;
    }

    public static float convertFloatTo2Decimal(float temp){
        DecimalFormat df = new DecimalFormat("#.00");
        temp = Float.valueOf(df.format(temp));
        return temp;
    }
}
