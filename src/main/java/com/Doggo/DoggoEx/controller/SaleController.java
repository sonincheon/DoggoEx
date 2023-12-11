package com.Doggo.DoggoEx.controller;


import com.Doggo.DoggoEx.dto.MemberReqDto;
import com.Doggo.DoggoEx.dto.MemberResDto;
import com.Doggo.DoggoEx.dto.SaleDto;
import com.Doggo.DoggoEx.service.AuthService;
import com.Doggo.DoggoEx.service.MemberService;
import com.Doggo.DoggoEx.service.SaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sale")
//@CrossOrigin(origins = CORS_ORIGIN)
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;
    // 구매 등록
    @PostMapping("/new")
    public ResponseEntity<Boolean> saleReg(@RequestBody SaleDto saleDto) {
        return ResponseEntity.ok(saleService.saveSale(saleDto));
    }
    // 구매내역 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> saleDelete(@PathVariable Long id) {
        boolean isTrue = saleService.deleteSale(id);
        return ResponseEntity.ok(isTrue);
    }

    // 정기배송날짜 수정
    @PutMapping("/modifyAuto/{id}")
    public ResponseEntity<Boolean> saleAutoModify(@PathVariable Long id, @RequestBody SaleDto saleDto) {
        boolean isTrue = saleService.saleAutoDelChange(id, saleDto);
        return ResponseEntity.ok(isTrue);
    }
    // 배송날짜 수정
    @PutMapping("/modify/{id}")
    public ResponseEntity<Boolean> saleModify(@PathVariable Long id, @RequestBody SaleDto saleDto) {
        boolean isTrue = saleService.saleDeliveryChange(id, saleDto);
        return ResponseEntity.ok(isTrue);
    }
    // 배송지 수정
    @PutMapping("/modifyAddr/{id}")
    public ResponseEntity<Boolean> saleAddrChange(@PathVariable Long id, @RequestBody SaleDto saleDto) {
        boolean isTrue = saleService.saleAddrChange(id, saleDto);
        return ResponseEntity.ok(isTrue);
    }

    // 회원 이메일로 게시글 조회
    @GetMapping("/list/email")
    public ResponseEntity<List<SaleDto>> salesListByEmail(@RequestParam String email) {
        List<SaleDto> list = saleService.getSalesListByEmail(email);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/list/all")
    public ResponseEntity<List<SaleDto>> salesListByAll() {
        List<SaleDto> list = saleService.getSalesListAll();
        return ResponseEntity.ok(list);
    }
}
