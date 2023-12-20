package com.Doggo.DoggoEx.repository;


import com.Doggo.DoggoEx.dto.DogDto;
import com.Doggo.DoggoEx.entity.Dog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DogRepository extends JpaRepository<Dog, Long> {
    Optional<Dog> findByKoreanName(String koreanName);

    @Query("SELECT new com.Doggo.DoggoEx.dto.DogDto(d.id, d.name, d.koreanName, d.imageLink) FROM Dog d ORDER BY d.koreanName ASC")
    List<DogDto> findAllByOrderByNameAsc(Pageable pageable);
}