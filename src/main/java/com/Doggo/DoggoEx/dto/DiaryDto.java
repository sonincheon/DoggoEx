package com.Doggo.DoggoEx.dto;


import com.Doggo.DoggoEx.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDto {
    private Long diaryId;
    private String diaryTitle; //제목
    private String diaryDetail; // 내용
    private LocalDateTime diaryWritedate; //작성일자
    private String memberId; // 작성자
}
