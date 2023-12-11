package com.Doggo.DoggoEx.controller;
import com.Doggo.DoggoEx.dto.WeatherDto;
import com.Doggo.DoggoEx.service.weather.*;
import com.Doggo.DoggoEx.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final MiddleWeatherService middleWeatherService;
    private final ShortWeatherService shortWeatherService;

    private final CompleteWeatherService completeWeatherService;

    private final WeatherDataSaveService weatherDataSaveService;

    private final WeatherToFrontService weatherToFrontService;

    public WeatherController(MiddleWeatherService middleWeatherService, ShortWeatherService shortWeatherService, CompleteWeatherService completeWeatherService, WeatherDataSaveService weatherDataSaveService, WeatherToFrontService weatherToFrontService) {
        this.middleWeatherService = middleWeatherService;
        this.shortWeatherService = shortWeatherService;
        this.completeWeatherService = completeWeatherService;
        this.weatherDataSaveService = weatherDataSaveService;
        this.weatherToFrontService = weatherToFrontService;
    }



    // 스케쥴러 구현으로 인해 필요없어졌으나 , 테스트를 위해 남겨둠 , 고로 주석처리 , 외부API를 가공하여 DB에 INSERT하는 컨트롤러
    @PostMapping("/insert")
    public ResponseEntity<?> insertForcasts() {
        try {
            // 지역별 코드
            Map<String, String> locationCode = shortWeatherService.getLocationCode();

            // 단기예보
            Map<String, List<List<String>>> completeShort = shortWeatherService.completeShort(locationCode);

            // 중기예보
            Map<String, List<List<String>>> middleTemp = middleWeatherService.getMiddleTemp(locationCode);
            Map<String, List<List<String>>> middleCondition = middleWeatherService.getMiddleCondition(locationCode);
            Map<String, List<List<String>>> completeMiddle = middleWeatherService.getCompleteMiddle(middleTemp,middleCondition);

            // 단기예보 + 중기예보
            Map<String, List<List<String>>> completeWeather = completeWeatherService.getCompleteWeather(completeShort, completeMiddle);

            // 각 도시별 일주일 날씨 정보 db에 insert
            weatherDataSaveService.saveWeatherData(completeWeather);

            return ResponseEntity.ok(completeWeather);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DB에서 AXIOS API 요청에 맞춰 각 지역별 일주일 치 날씨 정보 형태로 가공해서 return하는 컨트롤러
    @GetMapping("/get")
    @JsonView(Views.Public.class)
    public ResponseEntity<Map<String, List<WeatherDto>>> getForcasts () {

        Map<String, List<WeatherDto>> weeklyWeather = weatherToFrontService.getWeatherData();
        return ResponseEntity.ok(weeklyWeather);
    }
}
