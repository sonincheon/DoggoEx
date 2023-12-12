package com.Doggo.DoggoEx.service;


import com.Doggo.DoggoEx.dto.QuestDto;
import com.Doggo.DoggoEx.entity.PetProfile;
import com.Doggo.DoggoEx.entity.Quest;
import com.Doggo.DoggoEx.repository.PetProfileRepository;
import com.Doggo.DoggoEx.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestService {
    private final QuestRepository questRepository;
    private final PetProfileRepository petProfileRepository;
    //퀘스트 등록
    public boolean saveQuest(QuestDto questDto) {
        try {
            Quest quest = new Quest();
            PetProfile petProfile = petProfileRepository.findById(questDto.getPetId()).orElseThrow(
                    () -> new RuntimeException("해당 펫이 존재하지 않습니다.")
            );
            quest.setQuest1(questDto.getQuest1());
            quest.setQuest2(questDto.getQuest2());
            quest.setQuest3(questDto.getQuest3());
            quest.setQuest4(questDto.getQuest4());
            quest.setQuest5(questDto.getQuest5());
            quest.setQuestPerformance(quest.getQuestPerformance());
            quest.setPetProfile(petProfile);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //퀘스트 변경
    public boolean modifyQuest(Long id,QuestDto questDto) {
        try {
            Quest quest =questRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 펫이 존재하지 않습니다.")
            );
            quest.setQuest1(questDto.getQuest1());
            quest.setQuest2(questDto.getQuest2());
            quest.setQuest3(questDto.getQuest3());
            quest.setQuest4(questDto.getQuest4());
            quest.setQuest5(questDto.getQuest5());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //강아지별 Quest 수행 상태  //펫아이디 , 날짜
    public QuestDto petQuestDetail(Long id, LocalDateTime day){
        Quest quest =questRepository.findByPetProfileIdAndQuestPerformance(id,day);
        return convertEntityToDto(quest);
    }

    //강아지 Quset 리스트 %(날짜 / 이메일)



    //Enttity Dto로 변환
    private QuestDto convertEntityToDto(Quest quest) {
        QuestDto questDto = new QuestDto();
        questDto.setQuestId(quest.getId());
        questDto.setPetId(quest.getPetProfile().getId());
        questDto.setPetName(quest.getPetProfile().getPetName());
        questDto.setPetImg(quest.getPetProfile().getImageLink());
        questDto.setQuest1(quest.getQuest1());
        questDto.setQuest2(quest.getQuest2());
        questDto.setQuest3(quest.getQuest3());
        questDto.setQuest4(quest.getQuest4());
        questDto.setQuest5(quest.getQuest5());
        questDto.setQuestPerformance(quest.getQuestPerformance());
        return questDto;
    }

}
