package com.vanshProject.journalApp.services;

import com.vanshProject.journalApp.Cache.AppCache;
import com.vanshProject.journalApp.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Autowired
    RestTemplate restTemplate;
    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    AppCache appCache;

    @Autowired
    RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("Weather_of" + city, WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }else {
            String finalApiUrl = appCache.APP_CACHE.get("weather_api").replace("<key>", apiKey).replace("<city>", city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApiUrl, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body != null){
                redisService.set("Weather_of" + city, body, 300l);
            }
            return body;
        }
    }

}
