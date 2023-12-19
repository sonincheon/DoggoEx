package com.Doggo.DoggoEx.repository;

import com.Doggo.DoggoEx.dto.CatDto;
import com.Doggo.DoggoEx.entity.Cat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CatRepository extends JpaRepository<Cat, Long> {
    Optional<Cat> findByName(String name);
    @Query("SELECT new com.Doggo.DoggoEx.dto.CatDto(c.id, c.name, c.imageLink) FROM Cat c ORDER BY c.name ASC")
    List<CatDto> findAllByOrderByNameAsc(Pageable pageable);

}

