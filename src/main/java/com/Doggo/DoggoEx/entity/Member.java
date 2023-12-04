package com.Doggo.DoggoEx.entity;


import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "member_tb")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_profile_seq")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<PetProfile> petProfiles;
    @Column(unique = true , nullable = false)
    private String memberEmail;
    @Column(nullable = false)
    private String memberPassword;
    private String memberImage;
    @Column(nullable = false)
    private String memberTel;
    @Column(nullable = false)
    private String memberGender;
    @Column(nullable = false)
    private String memberName;
    @Column(nullable = false)
    private Date memberBirth;
    @Column(nullable = false)
    private String memberAddress;
    private Date memberRegdate;

    @PrePersist
    protected void prePersist() {
        memberRegdate = new Date();
    }


    private String memberGrade;

}