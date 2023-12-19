package com.Doggo.DoggoEx.controller;


import com.Doggo.DoggoEx.dto.DogDto;
import com.Doggo.DoggoEx.utils.Views;
import com.Doggo.DoggoEx.service.DogService;
import com.Doggo.DoggoEx.service.EngToKorService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dogs")
public class DogController {

    private final DogService dogService;
    private final EngToKorService engToKorService;

    public DogController(DogService dogService, EngToKorService engToKorService) {
        this.dogService = dogService;
        this.engToKorService = engToKorService;
    }
    // 필요시에만 INSERT 하시오 , API 허용량 무셔
    @PostMapping("/insert")
    public ResponseEntity<?> insertDogs() {
        try {
            dogService.insertDogs();
            return ResponseEntity.ok("애견도감 테이블 insert");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/detail/{name}")
    //@ PathVariable에서  RequstParam으로 변경 , 주소에 쿼리파라미터로 전달하는게 url에 직접 기입방식보다 간결함
    public ResponseEntity<DogDto> getDogByName(@PathVariable String name) {
        try {
            DogDto dogDto = dogService.getDogByName(name);
            DogDto korDogDto = engToKorService.dogToKor(dogDto);
            return ResponseEntity.ok(korDogDto);
        } catch (Exception e) {
            // 데이터가 조회되지 않았을때 발생하는 에러를 처리하기 위한 예외처리
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/view/list")
    @JsonView(Views.Public.class)
    public ResponseEntity<List<DogDto>> getDogSimpleView(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "8") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<DogDto> dogDtos = dogService.getDogsSortedByKoreanName(pageable);
            List<DogDto> korDogDtos = dogDtos.stream()
                    .map(engToKorService::dogToKor)
                    .sorted(Comparator.comparing(DogDto::getName))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(korDogDtos);
        } catch (Exception e) {
            // 데이터가 조회되지 않았을때 발생하는 에러를 처리하기 위한 예외처리
            return ResponseEntity.notFound().build();
        }

    }
    


}
