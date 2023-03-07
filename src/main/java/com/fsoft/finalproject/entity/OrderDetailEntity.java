package com.fsoft.finalproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetailEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  // product_id
  @ManyToOne()
  @JoinColumn(name = "product_id", nullable = false)
  private ProductEntity productEntity;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "price")
  private long price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private OrderEntity orderEntity;
}
