package com.Doggo.DoggoEx.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FeedDto {
    private Long feedId;
    private String feedName;    // 사료이름
    private String feedImg;     // 사료사진
    private Integer feedPrice;   // 사료가격
    private String feedInfo;    // 사료정보
    private String feedType;  // 사료타입 개/고양
    private String feedSubscribe;   // 판매수
}
