package com.Doggo.DoggoEx.repository;

import com.Doggo.DoggoEx.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository <Member, Long> {
    Optional<Member> findByMemberEmail(String email);
    boolean existsByMemberEmail(String email);

    Optional<Member> findByMemberEmailAndMemberPassword(String email, String password);
}
