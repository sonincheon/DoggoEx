package com.Doggo.DoggoEx.repository;

import com.Doggo.DoggoEx.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByMemberMemberEmail(String memberEmail);}