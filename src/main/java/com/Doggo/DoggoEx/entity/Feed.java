package com.Doggo.DoggoEx.entity;
import com.Doggo.DoggoEx.enums.FeedType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feed")
public class Feed {
    @Id
    @Column(name = "feed_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "feed_name",nullable = false)
    private String feedName;    // 사료이름
    @Column(name = "feed_img",nullable = false)
    private String feedImg;     // 사료사진
    @Column(name = "feed_price",nullable = false)
    private String feedPrice;   // 사료가격
    @Column(name = "feed_info",nullable = false)
    private String feedInfo;    // 사료정보
    @Enumerated(EnumType.STRING)
    @Column(name = "feed_type",nullable = false)
    private FeedType feedType;  // 사료타입 개/고양이
    @Column(name = "feed_subscribe",nullable = false)
    private String feedSubscribe;   // 판매수
}
