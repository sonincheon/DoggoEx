package com.Doggo.DoggoEx.utils;


import com.Doggo.DoggoEx.service.StrayService;
import com.Doggo.DoggoEx.service.weather.CompleteWeatherService;
import com.Doggo.DoggoEx.service.weather.MiddleWeatherService;
import com.Doggo.DoggoEx.service.weather.ShortWeatherService;
import com.Doggo.DoggoEx.service.weather.WeatherDataSaveService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class Scheduler {


    private final MiddleWeatherService middleWeatherService;
    private final ShortWeatherService shortWeatherService;

    private final CompleteWeatherService completeWeatherService;

    private final WeatherDataSaveService weatherDataSaveService;

    private final StrayService strayService;



    public Scheduler(MiddleWeatherService middleWeatherService,
                     ShortWeatherService shortWeatherService,
                     CompleteWeatherService completeWeatherService,
                     WeatherDataSaveService weatherDataSaveService, StrayService strayService) {

        this.middleWeatherService = middleWeatherService;
        this.shortWeatherService = shortWeatherService;
        this.completeWeatherService = completeWeatherService;
        this.weatherDataSaveService = weatherDataSaveService;
        this.strayService = strayService;
    }


    @PostConstruct
    public void init() {
        // 서비스 시작 시 한 번 실행할 작업
        try {
            executeWeatherTasks();
            executeStrayTasks();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    // 초 분 시 일 월 요일
    @Scheduled(cron = "0 30 9 * * ?") // 매일 아침 6시에 실행
    public void executeWeatherTasks() throws JsonProcessingException {
        try {
            System.out.println("날씨 스케쥴러 시작 ! ! ! !");
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
            System.out.println("날씨 정보 insert 작동 ! ! ! ! !");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Scheduled(cron = "0 0 * * * ?") // 한시간마다 실행
    public void executeStrayTasks() throws JsonProcessingException {
        try {
            strayService.deleteAllStrayData();;
            System.out.println("유기동물 스케쥴러 시작 ! ! ! !");
            strayService.insertStrays();
            System.out.println("날씨 정보 insert 작동완료 ! ! ! ! !");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
