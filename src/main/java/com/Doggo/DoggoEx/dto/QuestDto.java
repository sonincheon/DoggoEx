package com.Doggo.DoggoEx.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestDto {
    private Long QuestId;
    private Boolean quest1; //양치
    private Boolean quest2; //산책
    private Boolean quest3; //교감
    private Boolean quest4; //1분건강
    private Boolean quest5; //빗질
    private Long PetId; //펫 아이디
    private String PetName; // 수행 펫 이름
    private String PetImg; // 수행 펫 이미지
    private Integer percent; // 1~5퀘스트 수행률
    private LocalDate questPerformance; //날짜
}
