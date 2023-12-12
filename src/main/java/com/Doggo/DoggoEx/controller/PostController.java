package com.Doggo.DoggoEx.controller;


import com.Doggo.DoggoEx.dto.BoardDto;
import com.Doggo.DoggoEx.entity.Member;
import com.Doggo.DoggoEx.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final BoardService boardService;
    // 문의 등록
    @PostMapping("/new")
    public ResponseEntity<Boolean> boardPlus(@RequestBody BoardDto boardDto) {
        boolean isTrue = boardService.saveBoard(boardDto);
        return ResponseEntity.ok(isTrue);
    }
    // 게시글 목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> oneBoard() {
        List<BoardDto> list = boardService.getOneBoard();
        return ResponseEntity.ok(list);
    }
    // 회원별 문의글 조회
    @GetMapping("/list/{email}")
    public ResponseEntity<List<BoardDto>> oneBoardByMemberEmail(@RequestParam String memberEmail){
        List<BoardDto> list = boardService.getOneBoardByMemberEmail(memberEmail);
        return ResponseEntity.ok(list);
    }
}
