package com.publicis.sapient.weatherpridictionapi.client;

import com.publicis.sapient.weatherpridictionapi.model.CityWeather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openWeatherClient",url="${feign.client.url}",decode404 = true)
public interface OpenWeatherClient {

    @RequestMapping(method = RequestMethod.GET)
    CityWeather getWeather(@RequestParam String q);
}
