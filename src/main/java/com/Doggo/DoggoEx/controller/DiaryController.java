package com.Doggo.DoggoEx.controller;

import com.Doggo.DoggoEx.dto.DiaryDto;
import com.Doggo.DoggoEx.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
//@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    //일기 작성
    @PostMapping("/new")
    public ResponseEntity<Boolean> diaryReg(@RequestBody DiaryDto diaryDto) {
        boolean isTrue = diaryService.saveDiary(diaryDto);
        return ResponseEntity.ok(isTrue);
    }
    //일기 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> diaryDelete(@PathVariable Long id) {
        boolean isTrue = diaryService.deleteDiary(id);
        return ResponseEntity.ok(isTrue);
    }
    //일기 수정
    @PutMapping("/modify/{id}")
    public ResponseEntity<Boolean> diaryModify(@PathVariable Long id, @RequestBody DiaryDto diaryDto) {
        boolean isTrue = diaryService.modifyDiary(id, diaryDto);
        return ResponseEntity.ok(isTrue);
    }
    //일기 출력
    @GetMapping("/detail/{email}/{date}")
    public ResponseEntity<DiaryDto> boardDetail(@PathVariable String email, String date) {
        DiaryDto diaryDto = diaryService.diaryDetail(email,LocalDate.parse(date));
        return ResponseEntity.ok(diaryDto);
    }
}
