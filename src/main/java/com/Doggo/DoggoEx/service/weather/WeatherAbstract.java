package com.Doggo.DoggoEx.service.weather;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
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

    protected Map<String, Integer> getDateParameters() {
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

    protected Map<String, String> createQueryParams(String regCode, Map<String, Integer> dateParams) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("reg", regCode);
        queryParams.put("tmef1", String.valueOf(dateParams.get("tomorrow")));
        queryParams.put("tmef2", String.valueOf(dateParams.get("sevenDaysAfter")));
        queryParams.put("help", "0");

        return queryParams;
    }
}
