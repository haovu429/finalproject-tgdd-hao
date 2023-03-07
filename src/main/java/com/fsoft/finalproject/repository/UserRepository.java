package com.fsoft.finalproject.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fsoft.finalproject.entity.UserEntity;

@Scope(value = "singleton")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
  UserEntity getUserByEmail(String email);

  @Query("SELECT u FROM UserEntity u WHERE u.email = ?1 AND u.password = ?2")
  UserEntity getUserByAccount(String email, String password);

  UserEntity findByEmail(String email);
}
