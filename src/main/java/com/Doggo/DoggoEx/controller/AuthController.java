package com.Doggo.DoggoEx.controller;


import com.Doggo.DoggoEx.dto.MemberReqDto;
import com.Doggo.DoggoEx.dto.MemberResDto;
import com.Doggo.DoggoEx.dto.TokenDto;
import com.Doggo.DoggoEx.jwt.TokenProvider;
import com.Doggo.DoggoEx.security.SecurityUtil;
import com.Doggo.DoggoEx.service.AuthService;
import com.Doggo.DoggoEx.service.EmailService;
import com.Doggo.DoggoEx.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MemberService memberService;
    private final EmailService emailService;
    private final TokenProvider tokenProvider;
    private final SecurityUtil securityUtil;
    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<MemberResDto> signup(@RequestBody MemberReqDto requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }
    //로그인
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

    @GetMapping("/findId")
    public ResponseEntity<String> findId(@RequestParam String name, @RequestParam String tel) {
        String memberEmail = authService.findId(name, tel);
        return ResponseEntity.ok(memberEmail);
    }

    // accessToken 재발급
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestBody String refreshToken) {
        log.info("refreshToken: {}", refreshToken);
        return ResponseEntity.ok(authService.createAccessToken(refreshToken));
    }

    // 이메일 인증코드 발송
    @PostMapping("/emailConfirm")
    public String emailConfirm(@RequestParam String email) throws Exception {
        String confirm = emailService.sendSimpleMessage(email);
        return confirm;
    }

    //로그인중 확인
    @GetMapping("/isLogin/{token}")
    public ResponseEntity<Boolean> isLogin(@PathVariable String token) {
        log.warn("token: {}", token);
        boolean isTrue = tokenProvider.validateToken(token);
        return ResponseEntity.ok(!isTrue);
    }

    //토큰값받고 이메일 출력
    @GetMapping("/takenEmail")
    public ResponseEntity<String> takenEmail() {
        String email=memberService.getEmail(securityUtil.getCurrentMemberId());
        return ResponseEntity.ok(email);
    }


}
