/** Hao Vu - haovu961@gmail.com - Jul 28, 2022 - 5:14:00 PM */
package com.fsoft.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "manufacturer")
public class ManufacturerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "name", columnDefinition = "VARCHAR(100)", unique = true)
  private String name;

  @OneToMany(mappedBy = "manufacturerEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ProductEntity> productEntities = new ArrayList<>();

}
