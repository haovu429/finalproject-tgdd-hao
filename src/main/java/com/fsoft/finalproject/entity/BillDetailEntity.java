package com.fsoft.finalproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bill_detail")
public class BillDetailEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "product_image", columnDefinition = "VARCHAR(200)")
  private String productImage;

  @Column(name = "product_name", columnDefinition = "VARCHAR(200)")
  private String productName;

  @Column(name = "origin_price")
  private int originPrice;

  @Column(name = "sale_price")
  private int salePrice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bill_id")
  private BillEntity billEntity;


}
