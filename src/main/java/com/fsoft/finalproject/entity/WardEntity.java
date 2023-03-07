/** Hao Vu - haovu961@gmail.com - Jul 28, 2022 - 10:22:52 PM */
package com.fsoft.finalproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ward")
public class WardEntity {

  @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "name", columnDefinition = "VARCHAR(50)")
  private String name;

  // district_id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = true)
  private DistrictEntity districtEntity;
}
