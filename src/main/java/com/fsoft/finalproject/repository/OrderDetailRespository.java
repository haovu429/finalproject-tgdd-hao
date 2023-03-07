package com.fsoft.finalproject.repository;

import com.fsoft.finalproject.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRespository extends JpaRepository<OrderDetailEntity, Long> {

  @Query("SELECT od FROM OrderDetailEntity od WHERE od.orderEntity.id = ?1")
  List<OrderDetailEntity> findAllByOrderId(long orderId);

  @Query(
      "SELECT SUM(od.price * od.quantity) FROM OrderDetailEntity od WHERE od.orderEntity.id = ?1")
  int getTotalPriceByOrderId(long orderId);

  OrderDetailEntity findOneById(long id);
}
