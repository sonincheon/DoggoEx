package com.Doggo.DoggoEx.repository;

import com.Doggo.DoggoEx.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository <Member, Long> {
}
