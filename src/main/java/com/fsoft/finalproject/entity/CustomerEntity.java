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
@Table(name = "customer")
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "full_name", columnDefinition = "VARCHAR(50)")
  private String fullName;

  @Column(name = "email", columnDefinition = "VARCHAR(100)")
  private String email;

  @Column(name = "phone", nullable = false, unique = true, columnDefinition = "VARCHAR(12)")
  private String phone;

  @Column(name = "gender", columnDefinition = "VARCHAR(10)")
  private String gender;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerEntity")
  private List<AddressEntity> addressEntities = new ArrayList<>();

  @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
  private List<OrderEntity> orderEntities = new ArrayList<>();

  public CustomerEntity(long id, String fullName) {
    this.id = id;
    this.fullName = fullName;
  }
}
