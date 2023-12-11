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
public class QuestDto {
    private Long QuestId;
    private Boolean quest1;
    private Boolean quest2;
    private Boolean quest3;
    private Boolean quest4;
    private Boolean quest5;
    private Member memberId; // 수행자
    private LocalDateTime questPerformance;
}
