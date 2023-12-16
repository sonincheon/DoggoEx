package com.Doggo.DoggoEx.service;

import com.Doggo.DoggoEx.dto.StrayDto;
import com.Doggo.DoggoEx.entity.Stray;
import com.Doggo.DoggoEx.repository.StrayRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

@Service
public class StrayService {

    private final StrayRepository strayRepository;
    private final RestTemplate restTemplate;

    @Value("${flask.url}")
    private String flaskUrl;

    public StrayService(StrayRepository strayRepository, RestTemplate restTemplate) {
        this.strayRepository = strayRepository;
        this.restTemplate = new RestTemplate();
    }

    public List<StrayDto> getStrays() {
        try {
            String jsonResponse = restTemplate.getForObject(flaskUrl, String.class);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonResponse, new TypeReference<List<StrayDto>>() {});
        } catch (Exception e) {
            // 오류 처리
            e.printStackTrace();
            return Collections.emptyList(); // 빈 리스트 반환
        }
    }

    public void insertStrays() {
        try {
            String jsonResponse = restTemplate.getForObject(flaskUrl, String.class);
            ObjectMapper mapper = new ObjectMapper();
            StrayDto[] strayDtos = mapper.readValue(jsonResponse, StrayDto[].class);

            for (StrayDto strayDto : strayDtos) {
                Stray stray = strayDto.toEntity();
                strayRepository.save(stray);
                System.out.println("stray ! ! !" + stray);
            }
        } catch (Exception e) {
            // 오류 처리
            e.printStackTrace();

        }



    }

}
