package com.fsoft.finalproject.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "order_date", columnDefinition = "TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @Column(name = "total")
  private int total;

  @Column(name = "order_code")
  private String orderCode;

  @Column(name = "status", columnDefinition = "VARCHAR(20)")
  private String status;

  @Column(name = "note", columnDefinition = "TEXT")
  private String note;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id")
  private StoreEntity storeEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private CustomerEntity customerEntity;

  @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
  private List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Payment_id")
  private PaymentEntity paymentEntity;
}
