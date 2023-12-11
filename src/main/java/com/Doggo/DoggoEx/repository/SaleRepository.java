package com.Doggo.DoggoEx.repository;


import com.Doggo.DoggoEx.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SaleRepository extends JpaRepository <Sale, Long> {
    List<Sale> findByMemberMemberEmail(String email);
}
