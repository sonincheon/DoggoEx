package com.Doggo.DoggoEx.entity;
import com.Doggo.DoggoEx.enums.SalesType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "SALE")
public class Sale {
    @Id
    @Column(name = "sales_num")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; //구매 넘버

    @Column(name = "sales_price")
    private Integer salesPrice; //구매 가격

    @Column(name = "sales_user")
    private String salesUser; // 구매자

    @Column(name = "sales_addr")
    private String salesAddr;  //배송지

    @Column(name = "sales_feednum")
    private String salesFeednum; //사료번호

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_type")
    private SalesType salesType; //구매종류

    @Column(name = "sales_regdate")
    private LocalDateTime salesRegDate; // 구매일자

    @Column(name = "sales_delivery")
    private LocalDateTime salesDelivery; //배송일자

    @Column(name = "sales_autodelivery")
    private Integer salesAutoDelivery; //정기 배송일자 10일이면>>10으로표시
}
