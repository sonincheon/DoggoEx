package com.Doggo.DoggoEx.repository;

import com.Doggo.DoggoEx.entity.PetProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetProfileRepository extends JpaRepository<PetProfile, Long> {
}
