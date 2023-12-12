package com.Doggo.DoggoEx.dto;

import com.Doggo.DoggoEx.entity.Member;
import com.Doggo.DoggoEx.enums.BoardType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Getter
@Setter
@Slf4j
@ToString

public class BoardDto {
    private Long id;
    private BoardType boardType;
    private String comment;
    private String boardImg;
    private String memberEmail;
    private Date regDate;
    private String answer;

}