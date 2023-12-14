package com.Doggo.DoggoEx.controller;
import com.Doggo.DoggoEx.dto.BoardDto;
import com.Doggo.DoggoEx.service.AdminBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/qna")
@RequiredArgsConstructor
public class AdminBoardController {
    private final AdminBoardService adminBoardService;

    // 문의 전체 조회 → PostController꺼 쓰면 될듯

    // 문의 1:1 상세 조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<BoardDto> boardDetail(@PathVariable Long id) {
        BoardDto boardDto = adminBoardService.getBoardDetail(id);
        return ResponseEntity.ok(boardDto);
    }

    // 문의 답변 업로드
    @PutMapping("/answer/{id}")
    public ResponseEntity<Boolean> answer(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        boolean isTrue = adminBoardService.QnaAnswer(id, boardDto.getAnswer());
        return ResponseEntity.ok(isTrue);
    }
}
