package com.Doggo.DoggoEx.service;


import com.Doggo.DoggoEx.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public boolean isMember(String email) {
        return memberRepository.existsByMemberEmail(email);
    }
}
