package com.fsoft.finalproject.entity;

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
@Table(name = "bill")
public class BillEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "bill_code", nullable = false, unique = true)
  private long billCode;

  @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(50)")
  private String status;

  @Column(name = "store_address", columnDefinition = "VARCHAR(200)")
  private String storeAddress;

  @Column(name = "total_price")
  private long totalPrice;

  @Column(name = "customer_name", columnDefinition = "VARCHAR(50)")
  private String customerName;

  @Column(name = "customer_phone", columnDefinition = "VARCHAR(12)")
  private String customerPhone;

  @Column(name = "receiver_address", columnDefinition = "VARCHAR(200)")
  private String receiverAddress;

  @Column(name = "receiving_time", columnDefinition = "TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP)
  private Date receivingTime;

  @Column(name = "note", columnDefinition = "TEXT")
  private String note;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "billEntity", cascade = CascadeType.ALL)
  private List<BillDetailEntity> billDetails = new ArrayList<>();
}
