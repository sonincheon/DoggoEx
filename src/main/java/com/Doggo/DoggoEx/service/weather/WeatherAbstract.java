package com.Doggo.DoggoEx.service.weather;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public abstract class WeatherAbstract {

    protected RestTemplate restTemplate;

    public WeatherAbstract() {
        this.restTemplate = new RestTemplate();
    }

    protected HttpHeaders createHeaders(String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("authKey", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    protected String sendGetRequest(String url, Map<String, String> queryParams, HttpHeaders headers) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        queryParams.forEach(builder::queryParam);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    protected Map<String, Integer> middleDaysParam() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        int today = Integer.parseInt(now.format(formatter));
        int tomorrow = today + 1;
        int sevenDaysAfter = today + 7;

        Map<String, Integer> dateParams = new HashMap<>();
        dateParams.put("today", today);
        dateParams.put("tomorrow", tomorrow);
        dateParams.put("sevenDaysAfter", sevenDaysAfter);

        return dateParams;
    }

    protected Map<String, String> middleQueryParams(String regCode, Map<String, Integer> dateParams) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("reg", regCode);
        queryParams.put("tmef1", String.valueOf(dateParams.get("tomorrow")));
        queryParams.put("tmef2", String.valueOf(dateParams.get("sevenDaysAfter")));
        queryParams.put("help", "0");

        return queryParams;

    }
    protected Map<String, Integer> shortDaysParam() {
        LocalDate today = LocalDate.now();

        // 어제 날짜 계산하기
        LocalDate yesterday = today.minusDays(1);

        // 오후 12시 시간 설정하기
        LocalTime noon = LocalTime.of(12, 0);

        LocalDateTime todayNoon = LocalDateTime.of(today, noon);

        // 어제 날짜와 오후 12시를 결합하여 LocalDateTime 객체 생성
        LocalDateTime yesterdayNoon = LocalDateTime.of(yesterday, noon);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");

        int intYesterdayNoon = Integer.parseInt(yesterdayNoon.format(formatter));

        int intTodayNoon = Integer.parseInt(todayNoon.format(formatter));

        Map<String, Integer> shortDateParams = new HashMap<>();
        shortDateParams.put("today", intYesterdayNoon);
        shortDateParams.put("2DaysAfter", intTodayNoon);

        return shortDateParams;
    }

    protected Map<String, String> shortQueryParams(String regCode, Map<String, Integer> shortDateParams) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("reg", regCode);
        queryParams.put("tmfc1", String.valueOf(shortDateParams.get("today")));
        queryParams.put("tmfc2", String.valueOf(shortDateParams.get("2DaysAfter")));
        queryParams.put("help", "0");

        return queryParams;

    }
}
