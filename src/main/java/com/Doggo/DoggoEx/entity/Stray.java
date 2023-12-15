package com.Doggo.DoggoEx.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "stray_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Stray {
    @Id
    @Column(name = "stray_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stray_seq")
    private Long id;

    private String region;

    private String city;

    private String breed;

    private Long animalNumber;

    private String imageLink;

}
