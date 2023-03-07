package com.fsoft.finalproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fsoft.finalproject.dto.PromotionDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.service.PromotionService;

@RestController
@RequestMapping(path = "/api/v1")
public class PromotionController {

  @Autowired private PromotionService promotionService;

  @GetMapping("/promotion")
  ResponseObject getAll() {
    return promotionService.getAll();
  }

  @GetMapping("/product/by-promotion-id/{id}")
  ResponseObject getProductByPromotion(@PathVariable long id) {
    return promotionService.getProductByPromotion(id);
  }

  @GetMapping("/promotion/by-product-id/{id}")
  ResponseObject getPromotionByProduct(@PathVariable long id) {
    return promotionService.getPromotionByProductId(id);
  }

  @PostMapping("/promotion")
  ResponseObject save(@RequestBody PromotionDTO promotion) {
    return promotionService.save(promotion);
  }

  @PutMapping("/updateProductsInPromotion/{id}")
  ResponseObject update(@PathVariable long id, @RequestBody ObjectNode objectNode) {
    List<String> listCode = new ArrayList<>();
    JsonNode arrNode = objectNode.get("products");
    if (arrNode.isArray()) {
      for (final JsonNode objNode : arrNode) {
        listCode.add(objNode.toString());
        System.out.println(objNode.toString());
      }
    }
    return promotionService.updateProducts(id, listCode);
  }

//  @PutMapping("/listProductInPromotion/{id}")
//  ResponseObject update(@PathVariable long id, @RequestBody ObjectNode objectNode) {
//    List<String> listCode = new ArrayList<>();
//    JsonNode arrNode = objectNode.get("products");
//    if (arrNode.isArray()) {
//      for (final JsonNode objNode : arrNode) {
//        listCode.add(objNode.toString());
//        System.out.println(objNode.toString());
//      }
//    }
//    return promotionService.updateProducts(id, listCode);
//  }

  @PutMapping("/promotionInformation/{id}")
  ResponseObject updateInformation(
      @PathVariable long id,
      @RequestBody @Validated PromotionDTO promotionDTO,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new RuntimeException(bindingResult.getFieldError().toString());
    } else {
      return promotionService.update(id, promotionDTO);
    }
  }

  @DeleteMapping("/promotion/{id}")
  ResponseObject delete(@PathVariable long id) {
    return promotionService.deletePromotion(id);
  }

  @DeleteMapping("/productFromPromotion/{id}/{code}")
  ResponseObject deleteProduct(@PathVariable long id, @PathVariable String code) {
    return promotionService.deletePromotionByProduct(id, code);
  }

  @GetMapping(
      value = "/promotion",
      params = {"productCode"})
  ResponseObject getPromotionByPromoCode(@RequestParam("productCode") String promoCode) {
    return promotionService.getPromotionByPromoCode(promoCode);
  }

}
