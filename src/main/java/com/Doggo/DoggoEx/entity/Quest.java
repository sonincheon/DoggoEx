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

    @Column(name = "quest_user",nullable = false)
    private String questUser;

    @Column(name = "quest_Performance",nullable = false)
    private LocalDateTime questPerformance;
}
