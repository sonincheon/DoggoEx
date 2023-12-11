package com.Doggo.DoggoEx.service;


import com.Doggo.DoggoEx.dto.SaleDto;
import com.Doggo.DoggoEx.entity.Feed;
import com.Doggo.DoggoEx.entity.Member;
import com.Doggo.DoggoEx.entity.Sale;
import com.Doggo.DoggoEx.repository.FeedRepository;
import com.Doggo.DoggoEx.repository.MemberRepository;
import com.Doggo.DoggoEx.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final MemberRepository memberRepository;
    private final FeedRepository feedRepository;

    // 판매 등록
    public boolean saveSale(SaleDto saleDto) {
        try {
            Sale sale = new Sale();
            Member member = memberRepository.findByMemberEmail(saleDto.getMemberId()).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            Feed feed = feedRepository.findByFeedName(saleDto.getFeedName()).orElseThrow(
                    () -> new RuntimeException("해당 사료가 존재하지 않습니다.")
            );
            sale.setSalesPrice(saleDto.getSalesPrice());
            sale.setMember(member);
            sale.setSalesAddr(saleDto.getSalesAddr());
            sale.setFeed(feed);
            sale.setSalesType(saleDto.getSalesType());
            sale.setSalesAutoDelivery(saleDto.getSalesAutoDelivery());
            saleRepository.save(sale);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // 정기배송날짜 수정
    public boolean saleAutoDelChange(Long id, SaleDto saleDto) {
        try {
            Sale sale = saleRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 판매 내역이 존재하지 않습니다.")
            );
            sale.setSalesAutoDelivery(saleDto.getSalesAutoDelivery());
            saleRepository.save(sale);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 배송날짜 수정
    public boolean saleDeliveryChange(Long id, SaleDto saleDto) {
        try {
            Sale sale = saleRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 판매 내역이 존재하지 않습니다.")
            );
            sale.setSalesDelivery(saleDto.getSalesDelivery());
            saleRepository.save(sale);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 배송지 수정
    public boolean saleAddrChange(Long id, SaleDto saleDto) {
        try {
            Sale sale = saleRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 판매 내역이 존재하지 않습니다.")
            );
            sale.setSalesAddr(saleDto.getSalesAddr());
            saleRepository.save(sale);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 구매 취소
    public boolean deleteSale(Long id) {
        try {
            saleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회원 이메일로 구매내역 조회
    public List<SaleDto> getSalesListByEmail(String email) {
        List<Sale> Sales = saleRepository.findByMemberMemberEmail(email);
        List<SaleDto> SaleDto = new ArrayList<>();
        for(Sale sale : Sales) {
            SaleDto.add(convertEntityToDto(sale));
        }
        return SaleDto;
    }

    // 전회원 구매내역 조회
    public List<SaleDto> getSalesListAll() {
        List<Sale> Sales = saleRepository.findAll();
        List<SaleDto> SaleDto = new ArrayList<>();
        for(Sale sale : Sales) {
            SaleDto.add(convertEntityToDto(sale));
        }
        return SaleDto;
    }

    // 세일 엔티티를 회원 DTO로 변환
    private SaleDto convertEntityToDto(Sale sale) {
        SaleDto saleDto = new SaleDto();
        saleDto.setSalesPrice(sale.getSalesPrice());//구매 가격
        saleDto.setSalesAddr(sale.getSalesAddr());//배송지
        saleDto.setMemberId(sale.getMember().getMemberEmail());// 구매자
        saleDto.setFeedName(sale.getFeed().getFeedName());// 사료이름
        saleDto.setSalesType(sale.getSalesType());//구매종류
        saleDto.setSalesRegDate(sale.getSalesRegDate()); // 구매일자
        saleDto.setSalesDelivery(sale.getSalesDelivery()); //배송일자
        saleDto.setSalesAutoDelivery(sale.getSalesAutoDelivery()); //정기 배송일자
        return saleDto;
    }
}