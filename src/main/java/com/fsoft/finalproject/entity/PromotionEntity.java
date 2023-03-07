/**
 * Hao Vu - haovu961@gmail.com - Jul 28, 2022 - 10:54:33 PM
 */
package com.fsoft.finalproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "promotion")
public class PromotionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "start_date")
  @Temporal(TemporalType.DATE)
  private Date startDay;

  @Column(name = "end_date")
  @Temporal(TemporalType.DATE)
  private Date endDate;

  @Column(name = "promo_code", unique = true, columnDefinition = "VARCHAR(10)")
  private String promoCode;

  @Column(name = "discount")
  private int discount;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "type", columnDefinition = "VARCHAR(20)")
  private String type;

  @ManyToMany(mappedBy = "promotionEntities", cascade = CascadeType.ALL)
  private List<ProductEntity> productEntities = new ArrayList<>();


  public void removeProduct(ProductEntity products) {
    this.productEntities.remove(products);
    products.getPromotionEntities().remove(this);
  }

  public void removeAll() {
    for (ProductEntity u : this.productEntities) {
      this.removeProduct(u);
    }

  }
}
