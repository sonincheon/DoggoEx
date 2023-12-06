package com.Doggo.DoggoEx.controller;
import com.Doggo.DoggoEx.service.weather.CompleteWeatherService;
import com.Doggo.DoggoEx.service.weather.MiddleWeatherService;
import com.Doggo.DoggoEx.service.weather.ShortWeatherService;
import com.Doggo.DoggoEx.service.weather.WeatherDataSaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final MiddleWeatherService middleWeatherService;
    private final ShortWeatherService shortWeatherService;

    private final CompleteWeatherService completeWeatherService;

    private final WeatherDataSaveService weatherDataSaveService;

    public WeatherController(MiddleWeatherService middleWeatherService, ShortWeatherService shortWeatherService, CompleteWeatherService completeWeatherService, WeatherDataSaveService weatherDataSaveService) {
        this.middleWeatherService = middleWeatherService;
        this.shortWeatherService = shortWeatherService;
        this.completeWeatherService = completeWeatherService;
        this.weatherDataSaveService = weatherDataSaveService;
    }



    // 스케쥴러 구현으로 인해 필요없어졌으나 , 테스트를 위해 남겨둠 , 고로 주석처리
//    @PostMapping("/insert")
//    public ResponseEntity<?> getForcasts() {
//        try {
//            // 지역별 코드
//            Map<String, String> locationCode = shortWeatherService.getLocationCode();
//
//            // 단기예보
//            Map<String, List<List<String>>> completeShort = shortWeatherService.completeShort(locationCode);
//
//            // 중기예보
//            Map<String, List<List<String>>> middleTemp = middleWeatherService.getMiddleTemp(locationCode);
//            Map<String, List<List<String>>> middleCondition = middleWeatherService.getMiddleCondition(locationCode);
//            Map<String, List<List<String>>> completeMiddle = middleWeatherService.getCompleteMiddle(middleTemp,middleCondition);
//
//            // 단기예보 + 중기예보
//            Map<String, List<List<String>>> completeWeather = completeWeatherService.getCompleteWeather(completeShort, completeMiddle);
//
//            // 각 도시별 일주일 날씨 정보 db에 insert
//            weatherDataSaveService.saveWeatherData(completeWeather);
//
//            return ResponseEntity.ok(completeWeather);
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//
//    }

}
