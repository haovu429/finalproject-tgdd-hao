//package com.fsoft.finalproject.repository;
//
//import java.util.List;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//@Scope(value = "singleton")
//public interface ProductVoteRepository extends JpaRepository<ProductVoteEntity, Long> {
//  @Query(
//      "SELECT new com.fsoft.finalproject.dto.ProductVoteDTO(c.id,c.rate) FROM ProductVoteEntity c  WHERE c.customerEntity.phone = :param1 AND c.productEntity.productCode = :param2 ")
//  List<ProductVoteDTO> getVoteByCustomerAndProduct(
//      @Param("param1") String phone, @Param("param2") String code);
//
//  @Query(
//      "SELECT new com.fsoft.finalproject.dto.ProductVoteDTO(c.id,c.rate) FROM ProductVoteEntity c  WHERE  c.productEntity.productCode = :param1 ")
//  List<ProductVoteDTO> getVoteByProduct2(@Param("param1") String code);
//}
//// AND u.productEntity.productCode = :param2
//// ,@Param("param2") String code
//// @Query("SELECT c FROM ProductVoteEntity c JOIN c.customerEntity p WHERE p.phone = :param1 ")
//// com.fsoft.finalproject.dto
