/** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 3:05:49 PM */
package com.fsoft.finalproject.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fsoft.finalproject.entity.ProvinceEntity;

import java.util.List;

@Scope(value = "singleton")
public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Long> {
  @Query("SELECT u FROM ProvinceEntity u WHERE u.name = ?1")
  ProvinceEntity getByName(String name);

  List<ProvinceEntity> findByOrderByNameAsc();

  List<ProvinceEntity> findByOrderByIdAsc();
}
