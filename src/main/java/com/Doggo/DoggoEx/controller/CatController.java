package com.Doggo.DoggoEx.controller;

import com.Doggo.DoggoEx.dto.CatDto;
import com.Doggo.DoggoEx.utils.Views;
import com.Doggo.DoggoEx.service.CatService;
import com.Doggo.DoggoEx.service.EngToKorService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cats")
public class CatController {

    private final CatService catService;
    private final EngToKorService engToKorService;


    public CatController(CatService catService, EngToKorService engToKorService) {

        this.catService = catService;
        this.engToKorService = engToKorService;
    }

//     필요시에만 INSERT 하시오 , API 허용량 무셔

    @PostMapping("/insert")
   public ResponseEntity<?> catInsert() {
        try {
            catService.insertCats();
            return ResponseEntity.ok("애묘도감 테이블 insert");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detail/{name}")
    //@ PathVariable에서  RequstParam으로 변경 , 주소에 쿼리파라미터로 전달하는게 url에 직접 기입방식보다 간결함
    public ResponseEntity<CatDto> getCatByName(@PathVariable String name) {
        try {
            CatDto catDto = catService.getCatByName(name);
            CatDto korCatDto = engToKorService.catToKor(catDto);
            return ResponseEntity.ok(korCatDto);
        } catch (Exception e) {
            // 데이터가 조회되지 않았을때 발생하는 에러를 처리하기 위한 예외처리
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/view/list")
    @JsonView(Views.Public.class)
    public ResponseEntity<List<CatDto>> getCatSimpleView() {
        try {
            List<CatDto> catDtos = catService.getCatsSortedByKoreanName();
            List<CatDto> korCatDtos = catDtos.stream()
                    .map(engToKorService::catToKor)
                    .sorted(Comparator.comparing(CatDto::getName))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(korCatDtos);
        } catch (Exception e) {
            // 데이터가 조회되지 않았을때 발생하는 에러를 처리하기 위한 예외처리
            return ResponseEntity.notFound().build();
        }

    }
}


