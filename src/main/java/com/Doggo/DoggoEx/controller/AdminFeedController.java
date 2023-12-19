package com.Doggo.DoggoEx.controller;

import com.Doggo.DoggoEx.dto.FeedDto;
import com.Doggo.DoggoEx.service.AdminFeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/feed")
@RequiredArgsConstructor
public class AdminFeedController {
    private final AdminFeedService adminFeedService;

    // 사료 전체 조회
    @GetMapping("/feeds")
    public ResponseEntity<List<FeedDto>> adminFeedList() {
        List<FeedDto> list = adminFeedService.getAdminFeedList();
        return ResponseEntity.ok(list);
    }

    // 페이지네이션
    @GetMapping("/list/page")
    public ResponseEntity<List<FeedDto>> feedList(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        List<FeedDto> list = adminFeedService.getFeedList(page, size);
        log.info("list : {}", list);
        return ResponseEntity.ok(list);
    }

    // 페이지 수 조회
    @GetMapping("/list/count")
    public ResponseEntity<Integer> feedCount(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        int pageCnt = adminFeedService.getFeedPage(pageRequest);
        return ResponseEntity.ok(pageCnt);
    }
}
