package com.Doggo.DoggoEx.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "pet_profile_tb")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PetProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_profile_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "animal_type_id")
    private AnimalType animalType; // 동물의 종류 (Enum 참조)

    private String petName; // 동물의 이름

    private String breed;

    private String imageLink;

    private LocalDate birthDate;


    private Date regDate; // 생성일

    @PrePersist
    protected void prePersist() {
        regDate = new Date();
    }
}