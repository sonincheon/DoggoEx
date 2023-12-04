package com.Doggo.DoggoEx.dto.weather;

import com.Doggo.DoggoEx.enums.CityEnum;
import com.Doggo.DoggoEx.enums.RegionEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumMapper {
    public static Map<String, List<String>> getRegionToCitiesMap() {
        Map<String, List<String>> regionToCities = new HashMap<>();

        // 예시: SEOUL_GYEONGGI 지역에 서울, 수원, 인천 추가
        regionToCities.put(RegionEnum.SEOUL_GYEONGGI.name(), Arrays.asList(CityEnum.서울.name(), CityEnum.수원.name(), CityEnum.인천.name()));
        regionToCities.put(RegionEnum.GANGWON_YOUNGSEO.name(), Arrays.asList(CityEnum.춘천.name()));

        return regionToCities;
    }
}
