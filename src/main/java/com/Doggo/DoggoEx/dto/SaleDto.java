package com.Doggo.DoggoEx.dto;


import com.Doggo.DoggoEx.enums.SalesType;
import lombok.*;


import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {
    private Long saleId; //구매 넘버
    private Integer salesPrice; //구매 가격
    private String memberId; // 구매자
    private String salesAddr;  //배송지
    private String feedName; // 사료이름
    private SalesType salesType; //구매종류
    private LocalDateTime salesRegDate; // 구매일자
    private LocalDateTime salesDelivery; //배송일자
    private Integer salesAutoDelivery; //정기 배송일자 10일이면>>10으로표시
}
