package com.Doggo.DoggoEx.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WEATHER_TB")
@Getter @Setter
@NoArgsConstructor
public class Weather {
    @Id
    @Column(name = "weather_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weather_seq")
    private Long id;
    @Column(unique = true)
    private String region ;

    private int weatherDate;

    private int minTemperature;

    private int maxTemperature;

    private int weatherCondition;

    private int rainPercent;






    private Date regDate;



    @PrePersist // DB에 INSERT 되기 전에 실행되는 메소드
    public void prePersist() {
        regDate = new Date();
    }
}
