/** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 3:39:05 PM */
package com.fsoft.finalproject.repository;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.finalproject.entity.WardEntity;

@Scope(value = "singleton")
public interface WardRepository extends JpaRepository<WardEntity, Long> {
  @Query("SELECT u FROM WardEntity u WHERE u.name = ?1")
  WardEntity getByName(String name);

  @Transactional
  @Modifying
  @Query("SELECT d FROM WardEntity d WHERE d.districtEntity.id = ?1")
  List<WardEntity> getByDistrictId(long districtId);

  @Transactional
  @Modifying
  @Query("DELETE FROM WardEntity w WHERE w.districtEntity.id = ?1")
  void deleteByDistrictId(long districtId);
}
