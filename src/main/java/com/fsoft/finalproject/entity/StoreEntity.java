package com.fsoft.finalproject.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "store")
public class StoreEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name", columnDefinition = "VARCHAR(100)")
  private String name;

  @Column(name = "phone", unique = true, columnDefinition = "VARCHAR(12)")
  private String phone;

  @Column(name = "fax", unique = true, columnDefinition = "VARCHAR(20)")
  private String fax;

  @Column(name = "email", unique = true, columnDefinition = "VARCHAR(50)")
  private String email;

  @Column(name = "image", columnDefinition = "VARCHAR(100)")
  private String image;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "open_hour", columnDefinition = "VARCHAR(50)")
  private String openHour;

  @Column(name = "iat")
  private float iat;

  @Column(name = "ing")
  private float ing;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", nullable = true)
  private AddressEntity addressEntity;

  @OneToMany(mappedBy = "storeEntity", cascade = CascadeType.REMOVE)
  private List<OrderEntity> orderEntities = new ArrayList<>();
}
