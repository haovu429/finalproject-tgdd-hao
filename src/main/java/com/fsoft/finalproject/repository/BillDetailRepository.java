package com.fsoft.finalproject.repository;

import com.fsoft.finalproject.entity.BillDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillDetailRepository extends JpaRepository<BillDetailEntity, Long> {
  @Query("SELECT b FROM BillDetailEntity b WHERE b.billEntity.id = ?1")
  List<BillDetailEntity> findAllByBillId(Long billId);

  BillDetailEntity findOneById(Long id);
}
