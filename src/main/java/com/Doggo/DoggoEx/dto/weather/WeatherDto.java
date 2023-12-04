package com.Doggo.DoggoEx.dto.weather;


import com.Doggo.DoggoEx.dto.View.Views;
import com.Doggo.DoggoEx.entity.Cat;
import com.Doggo.DoggoEx.entity.Weather;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
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
