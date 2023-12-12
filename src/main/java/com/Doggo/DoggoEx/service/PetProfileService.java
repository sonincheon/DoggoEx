package com.Doggo.DoggoEx.service;

import com.Doggo.DoggoEx.dto.PetProfileDto;
import com.Doggo.DoggoEx.entity.PetProfile;
import com.Doggo.DoggoEx.repository.PetProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetProfileService {
    private final PetProfileRepository petProfileRepository;


    // 반려동물 전체 조회
    public List<PetProfileDto> getPetProfileList() {
        List<PetProfile> petProfiles = petProfileRepository.findAll();
        List<PetProfileDto> petProfileDtos = new ArrayList<>();
        for(PetProfile petProfile : petProfiles) {
            petProfileDtos.add(convertEntityToDto(petProfile));
        }
        return petProfileDtos;
    }

    private PetProfileDto convertEntityToDto(PetProfile petProfile) {
        PetProfileDto petProfileDto = new PetProfileDto();
        petProfileDto.setId(petProfile.getId());
        petProfileDto.setMemberId(petProfile.getMember().getMemberEmail());
        petProfileDto.setAnimalType(petProfile.getAnimalType());
        petProfileDto.setPetName(petProfile.getPetName());
        petProfileDto.setBreed(petProfile.getBreed());
        petProfileDto.setImageLink(petProfile.getImageLink());
        petProfileDto.setBirthDate(petProfile.getBirthDate());
        petProfileDto.setRegDate(petProfile.getRegDate());
        return petProfileDto;
    }
}
