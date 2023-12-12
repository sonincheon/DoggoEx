package com.Doggo.DoggoEx.repository;

import com.Doggo.DoggoEx.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public  interface DiaryRepository extends JpaRepository<Diary, Long> {
    Diary findByMemberMemberEmailAndDiaryWriteDate (String Member, LocalDate day);


}
