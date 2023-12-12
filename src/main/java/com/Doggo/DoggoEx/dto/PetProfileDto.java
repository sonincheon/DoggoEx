package com.Doggo.DoggoEx.dto;

import com.Doggo.DoggoEx.entity.AnimalType;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PetProfileDto {
    private Long id;
    private String memberId;
    private AnimalType animalType; // 동물의 종류 (Enum 참조)
    private String petName; // 동물의 이름
    private String breed;
    private String imageLink;
    private LocalDate birthDate;
    private LocalDate regDate; // 생성일
}

