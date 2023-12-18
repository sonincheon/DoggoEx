package com.Doggo.DoggoEx.service;

import com.Doggo.DoggoEx.dto.MemberReqDto;
import com.Doggo.DoggoEx.dto.SaleDto;
import com.Doggo.DoggoEx.entity.Sale;
import com.Doggo.DoggoEx.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminSaleService {
    private final SaleRepository saleRepository;        // 판매
    private final SaleService saleService;  // SaleService의 convertEntityToDto를 사용하기 위해 가져옴

    // 전체 조회 → saleService에 있는거 쓰기로 함


    // 송장 입력 (수정하는 형식 가져옴 - 빈 json에 내용을 채워서 저장하는 방식으로?)
//    @Transactional // 예외 발생시 롤백
    public boolean invoiceNum(Long id, Integer invoice) {
        try {
            if (invoice == null) {
                throw new IllegalArgumentException("송장번호는 비워둘 수 없습니다.");
            }
            String invoiceStr = String.valueOf(invoice);    // 문자타입으로 바꿔서 String인지 Integer인지 구분.
            if(!invoiceStr.matches("[0-9]+")) {
                throw new IllegalArgumentException("송장번호는 숫자로만 작성 가능합니다.");
            }
            Sale sale = saleRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 판매 내역이 존재하지 않습니다.")
            );
            sale.setInvoice(invoice);   // 송장번호를 받음
            saleRepository.save(sale);  // 송장번호 "" 에서 변경된거 저장
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
    // 페이지네이션
    public List<SaleDto> getSaleList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Sale> sales = saleRepository.findAll(pageable).getContent();
        List<SaleDto> saleDtos = new ArrayList<>();
        for(Sale sale : sales) {
            saleDtos.add(saleService.convertEntityToDto(sale));
        }
        return saleDtos;
    }

    // 페이지 수 계산
    public int getSalePage(Pageable pageable) {
        return saleRepository.findAll(pageable).getTotalPages();
    }


}
