package com.publicis.sapient.weatherpridictionapi.utils;

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
}
