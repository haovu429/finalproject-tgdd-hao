package com.fsoft.finalproject.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fsoft.finalproject.dto.PromotionDTO;
import com.fsoft.finalproject.dto.ResponseObject;

public interface PromotionService {

  ResponseObject save(PromotionDTO promotion);

  ResponseObject updateProducts(long id, List<String> codes);

  ResponseObject update(long id, PromotionDTO promotion);

  ResponseObject deletePromotion(long code);

  ResponseObject deletePromotionByProduct(long code, String product);

  ResponseObject getProductByPromotion(long id);

  ResponseObject getAll();

  ResponseObject getPromotionByPromoCode(String promoCode);

  ResponseObject getPromotionByProductId(long id);
}
