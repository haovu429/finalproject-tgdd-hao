package com.fsoft.finalproject.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ward_id", referencedColumnName = "id", nullable = false)
  private WardEntity wardEntity;

  @Column(name = "num_House", columnDefinition = "VARCHAR(200)")
  private String numHouse;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(
      name = "customer_address",
      joinColumns = @JoinColumn(name = "address_id"),
      inverseJoinColumns = @JoinColumn(name = "customer_id"))
  private CustomerEntity customerEntity;

  @OneToOne(mappedBy = "addressEntity", fetch = FetchType.LAZY)
  private StoreEntity storeEntity;

  // https://stackoverflow.com/questions/9944137/have-jpa-hibernate-to-replicate-the-on-delete-set-null-functionality
  // https://www.youtube.com/watch?v=GBSl4SIQuCg
  @PreRemove
  private void preRemove() {
    storeEntity.setAddressEntity(null);
  }



}
