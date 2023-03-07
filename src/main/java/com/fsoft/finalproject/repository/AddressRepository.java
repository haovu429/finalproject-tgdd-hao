package com.fsoft.finalproject.repository;

import com.fsoft.finalproject.entity.AddressEntity;
import com.fsoft.finalproject.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

  @Transactional
  @Modifying
  @Query(
      "select id from AddressEntity where  wardEntity.id = :wardId and numHouse = :numHouse")
  List<Long> checkStoreAddressExist(
      @Param("wardId") long wardId,
      @Param("numHouse") String numHouse);

  @Query("SELECT a FROM AddressEntity a WHERE a.customerEntity.phone = :customerPhone")
  List<AddressEntity> findAllByCustomerPhone(String customerPhone);
}
