/** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 3:36:00 PM */
package com.fsoft.finalproject.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.finalproject.entity.DistrictEntity;

@Scope(value = "singleton")
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
  @Query("SELECT u FROM DistrictEntity u WHERE u.name = ?1")
  DistrictEntity getByName(String name);

  @Transactional
  @Modifying
  @Query("SELECT d FROM DistrictEntity d WHERE d.provinceEntity.id = ?1")
  List<DistrictEntity> getByProvinceId(long provinceId);

  @Transactional
  @Modifying
  @Query("DELETE FROM DistrictEntity d WHERE d.provinceEntity.id = ?1")
  void deleteByProvinceId(long provinceId);
}
