/** Hao Vu - haovu961@gmail.com - Jul 28, 2022 - 10:46:48 PM */
package com.fsoft.finalproject.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;

  @Column(name = "name", columnDefinition = "VARCHAR(150)")
  private String productName;

  @Column(name = "product_code", unique = true, columnDefinition = "VARCHAR(50)")
  private String productCode;

  @Column(name = "category", columnDefinition = "VARCHAR(50)")
  private String category;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "price")
  private int price;

  @Column(name = "os", columnDefinition = "VARCHAR(20)")
  private String os;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "manufacturer_id")
  private ManufacturerEntity manufacturerEntity;

  @Column(name = "warranty")
  private int warranty;

  @Column(name = "standard_kit", columnDefinition = "VARCHAR(500)")
  private String standardKit;

  @Column(name = "advantages", columnDefinition = "TEXT")
  private String advantages;

  @Column(name = "disadvantages", columnDefinition = "TEXT")
  private String disadvantages;

  @Column(name = "included", columnDefinition = "TEXT")
  private String included;

  @Column(name = "article", columnDefinition = "TEXT")
  private String article;

  @Column(name = "num_side_photo")
  private int numSidePhoto;

  @Column(name = "num_view")
  private int numView;

  @OneToOne(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  private PhoneEntity phoneEntity;

  @OneToOne(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  private LaptopEntity laptopEntity;

  @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ImageEntity> imageEntities = new ArrayList<>();

  @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<VoteEntity> voteEntities = new ArrayList<>();

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "product_promotion",
      joinColumns = {@JoinColumn(name = "product_id")},
      inverseJoinColumns = {@JoinColumn(name = "promotion_id")})
  private List<PromotionEntity> promotionEntities = new ArrayList<>();

  @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ProductStoreEntity> productStoreEntities = new ArrayList<>();

  @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
}
