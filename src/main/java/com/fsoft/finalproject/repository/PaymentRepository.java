package com.fsoft.finalproject.repository;

import com.fsoft.finalproject.entity.PaymentEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
  PaymentEntity findOneById(long paymentId);

  List<PaymentEntity> findAll();
}
