package com.Doggo.DoggoEx.repository;

import com.Doggo.DoggoEx.entity.Board;
import com.Doggo.DoggoEx.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public  interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByMemberMemberEmailAndDiaryWritedate (String Member, LocalDateTime day);

}
