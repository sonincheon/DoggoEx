package com.Doggo.DoggoEx.controller;


import com.Doggo.DoggoEx.dto.FeedDto;
import com.Doggo.DoggoEx.enums.FeedType;
import com.Doggo.DoggoEx.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
//@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    // 사료 등록
    @PostMapping("/new")
    public ResponseEntity<Boolean> feedReg(@RequestBody FeedDto feedDto) {
        boolean isTrue = feedService.saveFeed(feedDto);
        return ResponseEntity.ok(isTrue);
    }
    // 사료 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> boardDelete(@PathVariable Long id) {
        boolean isTrue = feedService.deleteFeed(id);
        return ResponseEntity.ok(isTrue);
    }
    //개 ,고양이 사료별 조회
    @GetMapping("/list/type")
    public ResponseEntity<List<FeedDto>> boardListByEmail(@RequestParam FeedType type) {
        List<FeedDto> list = feedService.getFeedList(type);
        return ResponseEntity.ok(list);
    }
    //사료 판매 완료후 판매수 증가
    @GetMapping("/detail/{id}")
    public ResponseEntity<Boolean> boardDetail(@PathVariable Long id) {
        Boolean isTrue = feedService.cntFeed(id);
        return ResponseEntity.ok(isTrue);
    }
}
