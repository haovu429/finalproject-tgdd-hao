package com.fsoft.finalproject.repository;

import com.fsoft.finalproject.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<BillEntity, Long> {
  BillEntity findOneById(long id);

  List<BillEntity> findAll();

  List<BillEntity> findAllByCustomerPhone(String customerPhone);

  //    Query bill has max total price
  @Query(
      "SELECT b FROM BillEntity b WHERE b.totalPrice = (SELECT MAX(b.totalPrice) FROM BillEntity b)")
  BillEntity findBillHasMaxTotalPrice();
}
