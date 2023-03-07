package com.fsoft.finalproject.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fsoft.finalproject.entity.PromotionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Scope(value = "singleton")
public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {
  PromotionEntity findByPromoCode(String promoCode);

  //    findByPromoCode and has start date < now < endDate
  @Query("SELECT p FROM PromotionEntity p WHERE p.promoCode = :promoCode AND p.startDay <= current_date AND p.endDate >= current_date ")
  PromotionEntity findByPromoCodeAndStartDateAndEndDate(@Param("promoCode") String promoCode);
}
