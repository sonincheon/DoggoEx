package com.Doggo.DoggoEx.utils;


import com.Doggo.DoggoEx.service.weather.CompleteWeatherService;
import com.Doggo.DoggoEx.service.weather.MiddleWeatherService;
import com.Doggo.DoggoEx.service.weather.ShortWeatherService;
import com.Doggo.DoggoEx.service.weather.WeatherDataSaveService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class WeatherScheduler {
    private final MiddleWeatherService middleWeatherService;
    private final ShortWeatherService shortWeatherService;

    private final CompleteWeatherService completeWeatherService;

    private final WeatherDataSaveService weatherDataSaveService;

    public WeatherScheduler(MiddleWeatherService middleWeatherService,
                            ShortWeatherService shortWeatherService,
                            CompleteWeatherService completeWeatherService,
                            WeatherDataSaveService weatherDataSaveService) {
        this.middleWeatherService = middleWeatherService;
        this.shortWeatherService = shortWeatherService;
        this.completeWeatherService = completeWeatherService;
        this.weatherDataSaveService = weatherDataSaveService;
    }

    @Scheduled(cron = "0 0 12 * * ?") // 매일 아침 6시에 실행
    public void executeWeatherTasks() {
        try {
            // 데이터 insert하기전 날씨테이블의 모든 레코드 삭제 , 이는 최신화된 정보만 보관을 위함
            weatherDataSaveService.deleteAllWeatherData();

            // 지역별 코드
            Map<String, String> locationCode = shortWeatherService.getLocationCode();

            // 단기예보
            Map<String, List<List<String>>> completeShort = shortWeatherService.completeShort(locationCode);

            // 중기예보
            Map<String, List<List<String>>> middleTemp = middleWeatherService.getMiddleTemp(locationCode);
            Map<String, List<List<String>>> middleCondition = middleWeatherService.getMiddleCondition(locationCode);
            Map<String, List<List<String>>> completeMiddle = middleWeatherService.getCompleteMiddle(middleTemp, middleCondition);

            // 단기예보 + 중기예보
            Map<String, List<List<String>>> completeWeather = completeWeatherService.getCompleteWeather(completeShort, completeMiddle);

            // 각 도시별 일주일 날씨 정보 db에 insert
            weatherDataSaveService.saveWeatherData(completeWeather);
        } catch (Exception e) {
            e.printStackTrace();
            // 적절한 예외 처리 로직 구현
        }
    }

}
