package com.fsoft.finalproject.repository;

import com.fsoft.finalproject.dto.ItemInCart;
import com.fsoft.finalproject.entity.AddressEntity;
import com.fsoft.finalproject.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

  Optional<StoreEntity> findStoreEntityByEmailAndPhone(String email, String phone);

  Optional<StoreEntity> findStoreEntityByEmailOrPhoneOrFax(String email, String phone, String fax);

  @Query("SELECT s FROM StoreEntity s WHERE s.addressEntity.id = :addressId")
  Optional<StoreEntity> findStoreEntiyByAddressId(Long addressId);

  @Query("SELECT s FROM StoreEntity s WHERE s.addressEntity.wardEntity.districtEntity.id = :districtId")
  List<StoreEntity> findStoreEntityByDistrict(long districtId);

  @Query("SELECT s FROM StoreEntity s WHERE" +
          "  s.addressEntity.wardEntity.districtEntity.provinceEntity.id = :provinceId")
  List<StoreEntity> findStoreEntityByProvince(long provinceId);


  //Get list Store's address by list store id
  @Query("SELECT a FROM StoreEntity s, AddressEntity a WHERE s.addressEntity.id = a.id AND s.id IN :storeIds")
  List<AddressEntity> findAddressEntityByStoreIds(@Param("storeIds") List<Long> storeIds);

}
