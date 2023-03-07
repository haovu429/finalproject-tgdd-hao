/** Hao Vu - haovu961@gmail.com - Jul 28, 2022 - 10:18:11 PM */
package com.fsoft.finalproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "province")
public class ProvinceEntity {

  @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "name", columnDefinition = "VARCHAR(50)", unique = true)
  private String name;

  @OneToMany(mappedBy = "provinceEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<DistrictEntity> districtEntities = new ArrayList<>();
}
