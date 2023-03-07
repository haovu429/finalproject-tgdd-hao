package com.fsoft.finalproject.repository;

import com.fsoft.finalproject.entity.BillEntity;
import com.fsoft.finalproject.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
  OrderEntity findOneById(long orderId);

  @Query("SELECT o FROM OrderEntity o WHERE o.customerEntity.phone = :customerPhone")
  List<OrderEntity> findAllByCustomerPhone(String customerPhone);

  List<OrderEntity> findAll();
}
