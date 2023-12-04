package com.Doggo.DoggoEx.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quset")
public class Quest {
    @Id
    @Column(name = "quest_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quest_title",nullable = false)
    private String questTitle;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 전략
    @JoinColumn(name = "member_id") // 외래키
    private Member member; // 수행자

    @Column(name = "quest_Performance",nullable = false)
    private LocalDateTime questPerformance;
}
