package com.Doggo.DoggoEx.service.weather;

import com.Doggo.DoggoEx.enums.CityEnum;
import com.Doggo.DoggoEx.enums.RegionEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WeatherService extends WeatherAbstract {

    @Value("${api.weatherLocation.url}")
    private String weatherLocationUrl;
    @Value("${api.temperature7days.url}")
    private String temperature7daysUrl;

    @Value("${api.condition7days.url}")
    private String condition7daysUrl;

    @Value("${api.weatherApi.key}")
    private String weatherApiKey;

    public Map<String, String> getLocationCode() {
        HttpHeaders headers = createHeaders(weatherApiKey);
        String response = sendGetRequest(weatherLocationUrl, Collections.emptyMap(), headers);

        Map<String, String> locationCode = new HashMap<>();
        String[] lines = response.split("\n");
        for (String line : lines) {
            String[] parts = line.split("\\s+");
            if (parts.length >= 5) {
                String regId = parts[0];
                String regName = parts[4];
                locationCode.put(regName, regId);
//                System.out.println(regName+ " : " + regId);
            }
        }
//        System.out.println(locationAndRegCode);
        return locationCode;
    }

    public Map<String, List<List<String>>> getMiddleTemp(Map<String, String> locationCode) {
        HttpHeaders headers = createHeaders(weatherApiKey);
        Map<String, Integer> dateParams = getDateParameters();

        Map<String, List<List<String>>> middleTemp = new HashMap<>();

        for (CityEnum city : CityEnum.values()) {
            String regCode = locationCode.get(city.name());

            Map<String, String> queryParams = createQueryParams(regCode, dateParams);

            String response = sendGetRequest(temperature7daysUrl, queryParams, headers);


            String[] lines = response.split("\n");
            String[] filterLines = Arrays.copyOfRange(lines, 2, lines.length - 1);

            List<List<String>> cityWeather = new ArrayList<>();

            for (String index : filterLines) {
                String[] targets = index.split("\\s+");

                List<String> dailyTemp = new ArrayList<>();

                String date = targets[2].substring(0,8); // 날짜 추출
                String minTemp = targets[6]; // 최저날씨
                String maxTemp = targets[7]; // 최고날씨

                dailyTemp.add(date);
                dailyTemp.add(minTemp);
                dailyTemp.add(maxTemp);
                cityWeather.add(dailyTemp);

            }
            middleTemp.put(city.name(), cityWeather);
        }

        return middleTemp;
    }

    public Map<String, List<List<String>>> getMiddleCondition(Map<String, String> locationCode) {
        HttpHeaders headers = createHeaders(weatherApiKey);
        Map<String, Integer> dateParams = getDateParameters();

        Map<String, List<List<String>>> middleCondition = new HashMap<>();

        for (RegionEnum region : RegionEnum.values()) {

            String regCode = locationCode.get(region.getRegionName());

            Map<String, String> queryParams = createQueryParams(regCode, dateParams);

            String response = sendGetRequest(condition7daysUrl, queryParams, headers);

            String[] lines = response.split("\n");
//
//          System.out.println(Arrays.toString(lines));
            String[] filterLines = Arrays.copyOfRange(lines, 2, lines.length - 1);

//            System.out.println(Arrays.toString(filterLines));

            List<List<String>> regionCondition = new ArrayList<>();

            for (String index : filterLines) {
                Pattern pattern = Pattern.compile("[^\\s\"]+|\"([^\"]*)\"");
                Matcher matcher = pattern.matcher(index);

                List<String> fields = new ArrayList<>();
                while (matcher.find()) {
                    if (matcher.group(1) != null) {
                        // 큰따옴표로 묶인 부분을 추가합니다.
                        fields.add(matcher.group(1));
                    } else {
                        // 큰따옴표로 묶이지 않은 부분을 추가합니다.
                        fields.add(matcher.group());
                    }
                }

                // 날짜, 날씨 조건, 강수량 필드를 추출합니다.
                // 큰따옴표로 묶인 부분을 고려하여 적절한 인덱스 값을 사용합니다.
                String date = fields.get(2); // 날짜 추출
                String condition = fields.get(fields.size() - 2); // 날씨 조건
                String rain = fields.get(fields.size() - 1); // 강수량

                List<String> dailyCondition = new ArrayList<>();
                dailyCondition.add(date);
                dailyCondition.add(condition);
                dailyCondition.add(rain);

                regionCondition.add(dailyCondition);
            }
            middleCondition.put(region.name(), regionCondition);

        }

        return middleCondition;
    }

    public Map<String, List<List<String>>> getCompleteMiddle(Map<String, List<List<String>>> middleTemp,
                                                          Map<String, List<List<String>>> middleCondition) {

        Map<String, List<List<String>>> completeMiddleWeather = new HashMap<>();
        return completeMiddleWeather;
    }

}
