package com.Doggo.DoggoEx.dto;

import com.Doggo.DoggoEx.dto.View.Views;
import com.Doggo.DoggoEx.entity.AnimalType;
import com.Doggo.DoggoEx.entity.Cat;
import com.Doggo.DoggoEx.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto {
    // @JsonIgnore 원래는 제외 대상이었는데 로직변경으로 변동됨
    private long id;

    // JsonView로 명시해 놓으면 RestController 측 메서드에 해당 어노테이션을
    // 명시해놓을시 해당 필드만 Json 직렬화 처리됨 개꿀띠
    @JsonProperty("member_email")
    private String memberEmail;

    @JsonProperty("member_password")
    private String memberPassword;


    @JsonProperty("member_image")
    private String memberImage;


    @JsonProperty("member_tel")
    private String memberTel;


    @JsonProperty("member_gender")
    private String memberGender;


    @JsonProperty("member_name")
    private String memberName;


    @JsonProperty("member_birth")
    private LocalDate memberBirth;


    @JsonProperty("member_address")
    private String memberAddress;

    @JsonProperty("reg_date")
    private LocalDateTime regDate;


    @JsonProperty("member_grade")
    private String memberGrade;


    // builder를 통해서 반복된 getter setter 사용 방지 , @Query 어노테이션이랑 호환 안됨
    //
    public Member toEntity() {
        return Member.builder()
                .memberEmail(this.getMemberEmail())
                .memberPassword(this.getMemberPassword())
                .memberImage(this.getMemberImage())
                .memberTel((this.getMemberTel()))
                .memberGender(this.getMemberGender())
                .memberName(this.getMemberName())
                .memberBirth(this.getMemberBirth())
                .memberAddress(this.getMemberAddress())
                .memberGrade(this.getMemberGrade())
                .build();
    }
}

