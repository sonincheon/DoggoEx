package com.Doggo.DoggoEx.entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "diary")
public class Diary {

    @Id
    @Column(name = "diary_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "diary_title",nullable = false)
    private String diaryTitle; //제목

    @Column(name = "diary_detail",length = 1000)
    private String diaryDetail; // 내용

    @Column(name = "diary_writedate",nullable = false)
    private LocalDateTime diaryWritedate; //작성일자

    @Column(name = "diary_writer",nullable = false)
    private String diaryWriter;   //작성자

    @Column(name = "diary_trophy")
    private String diaryTrophy; // 트로피

    @PrePersist
    public void prePersist() {
        diaryWritedate = LocalDateTime.now();
    }

}
