package com.Doggo.DoggoEx.service;

import com.Doggo.DoggoEx.dto.DiaryDto;
import com.Doggo.DoggoEx.entity.Diary;
import com.Doggo.DoggoEx.entity.Feed;
import com.Doggo.DoggoEx.entity.Member;
import com.Doggo.DoggoEx.repository.DiaryRepository;
import com.Doggo.DoggoEx.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;
    //일기 작성 (날짜/이메일)
    public boolean saveDiary(DiaryDto diaryDto) {
        try {
            Diary diary = new Diary();
            Member member = memberRepository.findByMemberEmail(diaryDto.getMemberId()).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            diary.setDiaryTitle(diaryDto.getDiaryTitle());
            diary.setDiaryDetail(diaryDto.getDiaryDetail());
            diary.setDiaryWriteDate(diaryDto.getDiaryWritedate());
            diary.setMember(member);
            diaryRepository.save(diary);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //일기 삭제 (날짜별, 이메일)
    public boolean deleteDiary(Long id) {
        try {
            diaryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //일기 수정 (날짜별, 이메일)
    public boolean modifyDiary(Long id, DiaryDto diaryDto) {
        try {
             Diary diary =diaryRepository.findById(id).orElseThrow(
                     ()->new RuntimeException("해당 일기가 존재하지않습니다.")
             );
            diary.setDiaryTitle(diaryDto.getDiaryTitle());
            diary.setDiaryDetail(diaryDto.getDiaryDetail());
            diaryRepository.save(diary);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //일기 출력 (날짜별, 이메일)
    public List<DiaryDto> modifyDiary(String Email,LocalDateTime day) {
        List<Diary> diarys = diaryRepository.findByMemberMemberEmailAndDiaryWriteDate(Email,day);
        List<DiaryDto> diaryDtos =new ArrayList<>();
        for(Diary diary : diarys){
            diaryDtos.add(convertEntityToDto(diary));
        }
        return diaryDtos;
    }


    // 게시글 엔티티를 DTO로 변환
    private DiaryDto convertEntityToDto(Diary diary) {
        DiaryDto diaryDto =new DiaryDto();
        diaryDto.setDiaryId(diary.getId());
        diaryDto.setDiaryTitle(diary.getDiaryTitle());//제목
        diaryDto.setDiaryDetail(diary.getDiaryDetail());// 내용
        diaryDto.setDiaryWritedate(diary.getDiaryWriteDate()); //날짜
        diaryDto.setMemberId(diary.getMember().getMemberName()); // 작성자
        return diaryDto;
    }

}
