package com.Doggo.DoggoEx.dto;


import com.Doggo.DoggoEx.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DiaryDto {
    private Long diaryId;
    private String diaryTitle; //제목
    private String diaryDetail; // 내용
    private LocalDateTime diaryWritedate; //작성일자
    private Member memberId; // 작성자
    private Integer countcom; // 트로피
}
