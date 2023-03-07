/**
 * Hao Vu - haovu961@gmail.com - Jul 28, 2022 - 10:37:22 PM
 */
package com.fsoft.finalproject.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_store")
public class ProductStoreEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  // product_id
  @ManyToOne()
  @JoinColumn(name = "product_id", nullable = false)
  private ProductEntity productEntity;

  // store_id
  @ManyToOne()
  @JoinColumn(name = "store_id", nullable = false)
  private StoreEntity storeEntity;

  @Column(name = "quantity", nullable = false)
  private int quantity;
}
