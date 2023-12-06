package com.Doggo.DoggoEx.dto;


import com.Doggo.DoggoEx.entity.Weather;
import lombok.*;



@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherDto {


    private long id;

    private String region;

    private int weatherDate;

    private int morningTemperature;

    private int morningRainPercent;

    private String morningWeatherCondition;

    private int afternoonTemperature;

    private int afternoonRainPercent;

    private String afternoonWeatherCondition;
    public Weather toEntity() {
        return Weather.builder()
                .region(this.getRegion())
                .weatherDate(this.getWeatherDate())
                .morningTemperature(this.getMorningTemperature())
                .morningRainPercent(this.getMorningRainPercent())
                .morningWeatherCondition(this.getMorningWeatherCondition())
                .afternoonTemperature(this.getAfternoonTemperature())
                .afternoonRainPercent(this.getAfternoonRainPercent())
                .afternoonWeatherCondition(this.getAfternoonWeatherCondition())
                .build();
    }
}
