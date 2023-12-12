package com.Doggo.DoggoEx.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "quset")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Quest {
    @Id
    @Column(name = "quest_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quest_1",columnDefinition = "boolean default false")
    private Boolean quest1;

    @Column(name = "quest_2",columnDefinition = "boolean default false")
    private Boolean quest2;

    @Column(name = "quest_3",columnDefinition = "boolean default false")
    private Boolean quest3;

    @Column(name = "quest_4",columnDefinition = "boolean default false")
    private Boolean quest4;

    @Column(name = "quest_5",columnDefinition = "boolean default false")
    private Boolean quest5;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 전략
    @JoinColumn(name = "pet_id") // 외래키
    private PetProfile petProfile; // 수행 펫

    @Column(name = "quest_Performance",nullable = false)
    private LocalDate questPerformance; //수행날짜

}
