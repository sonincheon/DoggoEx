package com.Doggo.DoggoEx.controller;


import com.Doggo.DoggoEx.dto.PetProfileDto;
import com.Doggo.DoggoEx.service.PetProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetProfileService petProfileService;

    @GetMapping("/list")
    public ResponseEntity<List<PetProfileDto>> petProfileList() {
        List<PetProfileDto> list = petProfileService.getPetProfileList();
        return ResponseEntity.ok(list);
    }
}
