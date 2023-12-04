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

    @ManyToOne(fetch = FetchType.LAZY) // 지연 전략
    @JoinColumn(name = "member_id") // 외래키
    private Member member; // 작성자

    @Column(name = "diary_trophy")
    private Integer countcom; // 트로피

    @PrePersist
    public void prePersist() {
        diaryWritedate = LocalDateTime.now();
    }

}
