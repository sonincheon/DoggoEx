package com.Doggo.DoggoEx.dto;


import lombok.*;

import java.util.Map;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherDto {


    private long id;

    private Map<String, String> region;

    private int weatherDate;

    private int minTemperature;

    private int maxTemperature;

    private int weatherCondition;

    private int rainPercent;



//    public Weather toEntity() {
//        return Weather.builder()
//                .region(this.getRegion())
//                .weatherDate(this.getWeatherDate())
//                .minTemperature((this.getMinTemperature()))
//                .maxTemperature(this.getMaxTemperature())
//                .weatherCondition(this.getWeatherCondition())
//                .rainPercent(this.getRainPercent())
//                .build();
//    }
}
