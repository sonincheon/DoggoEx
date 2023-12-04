package com.Doggo.DoggoEx.controller;


import com.Doggo.DoggoEx.dto.MemberReqDto;
import com.Doggo.DoggoEx.dto.MemberResDto;
import com.Doggo.DoggoEx.dto.TokenDto;
import com.Doggo.DoggoEx.service.AuthService;
import com.Doggo.DoggoEx.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = CORS_ORIGIN)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResDto> signup(@RequestBody MemberReqDto requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberReqDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }
    // 회원 존재 여부 확인
    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> memberExists(@PathVariable String email) {
        log.info("email: {}", email);
        boolean isTrue = memberService.isMember(email);
        return ResponseEntity.ok(!isTrue);
    }
    // accessToken 재발급
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestBody String refreshToken) {
        log.info("refreshToken: {}", refreshToken);
        return ResponseEntity.ok(authService.createAccessToken(refreshToken));
    }
}
