package com.fsoft.finalproject.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fsoft.finalproject.entity.RoleEntity;

@Scope(value = "singleton")
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
  RoleEntity findOneByCode(String code);
}
//
