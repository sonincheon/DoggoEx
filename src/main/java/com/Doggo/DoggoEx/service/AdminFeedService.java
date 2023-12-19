package com.Doggo.DoggoEx.service;

import com.Doggo.DoggoEx.dto.FeedDto;
import com.Doggo.DoggoEx.entity.Feed;
import com.Doggo.DoggoEx.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminFeedService {

    private final FeedRepository feedRepository;
    private final FeedService feedService;

    // 전체 사료 조회
    public List<FeedDto> getAdminFeedList() {
        List<Feed> feeds = feedRepository.findAll();
        List<FeedDto> feedDtos = new ArrayList<>();
        for (Feed feed : feeds) {
            feedDtos.add(feedService.convertEntityToDto(feed));
        }
        return feedDtos;
    }

    // 페이지네이션
    public List<FeedDto> getFeedList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Feed> feeds = feedRepository.findAll(pageable).getContent();
        List<FeedDto> feedDtos = new ArrayList<>();
        for(Feed feed : feeds) {
            feedDtos.add(feedService.convertEntityToDto(feed));
        }
        return feedDtos;
    }
    // 페이지 수 계산
    public int getFeedPage(Pageable pageable) {
        return feedRepository.findAll(pageable).getTotalPages();
    }
}
