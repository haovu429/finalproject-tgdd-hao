package com.fsoft.finalproject.repository;

import com.fsoft.finalproject.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Transactional
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {

  @Modifying
  @Query("delete from ProductEntity where id = :id")
  void deletePhone(@Param("id") long id);
}
