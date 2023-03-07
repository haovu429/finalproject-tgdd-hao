/** Hao Vu - haovu961@gmail.com - Jul 28, 2022 - 10:20:29 PM */
package com.fsoft.finalproject.entity;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "district")
public class DistrictEntity {

  @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "name", columnDefinition = "VARCHAR(50)")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "province_id", nullable = false)
  private ProvinceEntity provinceEntity;

  @OneToMany(mappedBy = "districtEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<WardEntity> wardEntities = new ArrayList<>();
}
