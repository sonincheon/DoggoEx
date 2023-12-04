package com.Doggo.DoggoEx.entity;
import com.Doggo.DoggoEx.dto.DogDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dog_tb")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Dog {
    @Id
    @Column(name = "dog_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dog_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animalTypeId", referencedColumnName = "animalTypeId")
    private AnimalType animalTypeId;

    @Column(unique = true)
    private String name;

    private String imageLink;

    private int goodWithChildren;

    private int goodWithOtherDogs;

    private int shedding;

    private int grooming;

    private int drooling;

    private int coatLength;

    private int goodWithStrangers;

    private int playfulness;

    private int protectiveness;

    private int trainability;

    private int energy;

    private int barking;

    private int minLifeExpectancy;

    private int maxLifeExpectancy;

    private int minHeightMale;

    private int maxHeightMale;

    private int minHeightFemale;

    private int maxHeightFemale;

    private int minWeightMale;

    private int maxWeightMale;

    private int minWeightFemale;

    private int maxWeightFemale;




    public DogDto toDto() {
        return DogDto.builder()
                .id(this.getId()) // ID 포함 시키는 것에 대해 주의 필요 (컨텍스트에 따라 다름)
                .name(this.getName())
                .imageLink(this.getImageLink())
                .goodWithChildren(this.getGoodWithChildren())
                .goodWithOtherDogs(this.getGoodWithOtherDogs())
                .shedding(this.getShedding())
                .grooming(this.getGrooming())
                .drooling(this.getDrooling())
                .coatLength(this.getCoatLength())
                .goodWithStrangers(this.getGoodWithStrangers())
                .playfulness(this.getPlayfulness())
                .protectiveness(this.getProtectiveness())
                .trainability(this.getTrainability())
                .energy(this.getEnergy())
                .barking(this.getBarking())
                .minLifeExpectancy(this.getMinLifeExpectancy())
                .maxLifeExpectancy(this.getMaxLifeExpectancy())
                .minHeightMale(this.getMinHeightMale())
                .maxHeightMale(this.getMaxHeightMale())
                .minHeightFemale(this.getMinHeightFemale())
                .maxHeightFemale(this.getMaxHeightFemale())
                .minWeightMale(this.getMinWeightMale())
                .maxWeightMale(this.getMaxWeightMale())
                .minWeightFemale(this.getMinWeightFemale())
                .maxWeightFemale(this.getMaxWeightFemale())
                .build();
    }
}
