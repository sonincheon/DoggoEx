package com.Doggo.DoggoEx.repository;


import com.Doggo.DoggoEx.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface QuestRepository extends JpaRepository<Quest, Long> {
    Quest findByPetProfileIdAndQuestPerformance (Long PetId, LocalDateTime day);


}
