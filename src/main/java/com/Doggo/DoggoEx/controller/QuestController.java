package com.Doggo.DoggoEx.controller;

import com.Doggo.DoggoEx.dto.QuestDto;
import com.Doggo.DoggoEx.service.QuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@CrossOrigin(origins = CORS_ORIGIN)
@RestController
@RequestMapping("/quest")
@RequiredArgsConstructor
public class QuestController {
    private final QuestService questService;
    //수행 등록
    @PostMapping("/new")
    public ResponseEntity<Boolean> questReg(@RequestBody QuestDto questDto){
        boolean isTrue =questService.saveQuest(questDto);
        return ResponseEntity.ok(isTrue);
    }
    // 수행정보 수정
    @PutMapping("/modify/{id}")
    public ResponseEntity<Boolean> modifyQuest(@PathVariable Long id, @RequestBody QuestDto questDto) {
        boolean isTrue = questService.modifyQuest(id, questDto);
        return ResponseEntity.ok(isTrue);
    }










}
