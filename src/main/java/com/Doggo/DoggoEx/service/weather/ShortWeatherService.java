package com.Doggo.DoggoEx.service.weather;

import com.Doggo.DoggoEx.enums.CityEnum;
import com.Doggo.DoggoEx.enums.RegionEnum;
import com.Doggo.DoggoEx.service.weather.EnumMapper;
import com.Doggo.DoggoEx.service.weather.WeatherAbstract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ShortWeatherService extends WeatherAbstract {

    @Value("${api.weatherLocation.url}")
    private String weatherLocationUrl;


    @Value("${api.weatherApi.key}")
    private String weatherApiKey;

    @Value("${api.weatherShortDays.url}")
    private String weatherShortDays;



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
        System.out.println("지역코드가져오기 성공");
        return locationCode;
    }


    public Map<String, List<List<String>>> completeShort(Map<String, String> locationCode) {
        HttpHeaders headers = createHeaders(weatherApiKey);
        Map<String, Integer> shortDateParams = shortDaysParam();

        Map<String, List<List<String>>> completeShort = new HashMap<>();

        LocalDate today = LocalDate.now();
        LocalDate twoDaysLater = today.plusDays(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        System.out.println("단기예보 취합 시작");

        for (CityEnum city : CityEnum.values()) {
            String regCode = locationCode.get(city.name());
            Map<String, String> queryParams = shortQueryParams(regCode, shortDateParams);

            String response = sendGetRequest(weatherShortDays, queryParams, headers);
            String[] lines = response.split("\n");
            List<String> filteredLines = new ArrayList<>();

            for (String line : lines) {
                if (!line.contains("#") && !line.contains("-99")) {
                    filteredLines.add(line);
                }
            }

            Map<String, List<String>> morningData = new LinkedHashMap<>();
            Map<String, List<String>> afternoonData = new LinkedHashMap<>();

            for (String line : filteredLines) {
                List<String> fields = parseLine(line);
                if (!fields.isEmpty()) {
                    String dateStr = fields.get(2).substring(0, 8); // 날짜 추출
                    LocalDate date = LocalDate.parse(dateStr, formatter);

                    if (!date.isBefore(today) && !date.isAfter(twoDaysLater)) {
                        if (fields.get(2).endsWith("0000")) { // 오전 데이터
                            morningData.put(dateStr, Arrays.asList(fields.get(12), fields.get(13), fields.get(16)));
                        } else if (fields.get(2).endsWith("1200")) { // 오후 데이터
                            afternoonData.put(dateStr, Arrays.asList(fields.get(12), fields.get(13), fields.get(16)));
                        }
                    }
                }
            }

            List<List<String>> cityWeather = new ArrayList<>();
            for (String date : morningData.keySet()) {
                List<String> combinedWeather = new ArrayList<>();
                combinedWeather.add(date); // 날짜
                combinedWeather.addAll(morningData.get(date)); // 오전 기온, 오전 강수확률, 오전 날씨 예보
                if (afternoonData.containsKey(date)) {
                    combinedWeather.addAll(afternoonData.get(date)); // 오후 강수확률, 오후 날씨 예보 (오후 기온 제외)
                } else {
                    combinedWeather.addAll(Arrays.asList("", "", "")); // 오후 데이터가 없는 경우 빈 값 추가
                }
                cityWeather.add(combinedWeather);
            }

            completeShort.put(city.name(), cityWeather);
        }
               System.out.println("단기예보 취합 성공");
        return completeShort;
    }

    private List<String> parseLine(String line) {
        List<String> dataFields = new ArrayList<>();
        Matcher matcher = Pattern.compile("[^\\s\"]+|\"([^\"]*)\"").matcher(line);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                dataFields.add(matcher.group(1));
            } else {
                dataFields.add(matcher.group());
            }
        }
//       System.out.println(dataFields);
        return dataFields;
    }
}



