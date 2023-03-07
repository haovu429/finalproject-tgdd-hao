package com.fsoft.finalproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.finalproject.dto.CustomerDTO;
import com.fsoft.finalproject.entity.CustomerEntity;

@Scope(value = "singleton")
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
//  @Query(
//      "SELECT new com.fsoft.finalproject.dto.CustomerDTO(c.id,c.fullName) FROM CustomerEntity c WHERE c.phone = :param1")
//  List<CustomerDTO> getCustomerByPhone(@Param("param1") String phone);

  @Query("SELECT c FROM CustomerEntity c WHERE c.id =?1")
  Optional<CustomerEntity> findById2(Long id);

  CustomerEntity findOneByEmail(String email);

  CustomerEntity findOneByPhone(String phone);

  CustomerEntity findOneById(Long id);
}
