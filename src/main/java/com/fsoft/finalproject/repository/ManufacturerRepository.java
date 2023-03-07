/**
 * Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 3:42:25 PM
 */
package com.fsoft.finalproject.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fsoft.finalproject.entity.ManufacturerEntity;

@Scope(value = "singleton")
public interface ManufacturerRepository extends JpaRepository<ManufacturerEntity, Long> {
  @Query("SELECT u FROM ManufacturerEntity u WHERE u.name = ?1")
  ManufacturerEntity getByName(String name);

}
