package com.Doggo.DoggoEx.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


// 스프링에 , 우리가 만든 어플리케이션은 아시아 서울의 시간대를 기준으로 작동한다는것을 명시
@Configuration
public class AppConfig {

    @PostConstruct
    public void init(){
        // 시스템의 기본 시간대를 서울 시간대로 설정
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
