package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.dto.ProductDTO;
import com.fsoft.finalproject.dto.ProductStoreDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.ProductStoreEntity;
import com.fsoft.finalproject.service.ProductStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/")
public class ProductStoreController {
  @Autowired
  ProductStoreService productStoreService;

  @GetMapping("product-stores")
  @ResponseBody
  public ResponseObject findAll(@RequestParam("page") Optional<Integer> page) {
    Pageable pageable = PageRequest.of(page.orElse(0), 5);
    Page<ProductStoreDTO> dtos = productStoreService.findAll(pageable);
    return new ResponseObject(HttpStatus.OK.value(), "success", dtos);
  }

  @PostMapping("product-store")
  public ResponseObject save(@RequestBody @Validated ProductStoreDTO dto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new RuntimeException(bindingResult.getFieldError().toString());
    }
    Optional<ProductStoreDTO> res = productStoreService.save(dto);
    if (!res.isPresent()) {
      throw new RuntimeException("Some error occured");
    }
    return new ResponseObject(HttpStatus.OK.value(), "success", res.get());

  }

  @PutMapping("product-store/{id}")
  public ResponseObject update(@PathVariable("id") long id, @RequestBody @Validated ProductStoreDTO dto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new RuntimeException(bindingResult.getFieldError().toString());
    }
    Optional<ProductStoreDTO> res = productStoreService.update(id, dto);
    if (!res.isPresent()) {
      throw new RuntimeException("Some error occured");
    }
    return new ResponseObject(HttpStatus.OK.value(), "success", res.get());
  }

  @DeleteMapping("product-store/{id}")
  public ResponseObject delete(@PathVariable("id") long id) {
    boolean res = productStoreService.delete(id);
    if (!res) {
      throw new RuntimeException("Not found product-store with id: " + id);
    }
    return new ResponseObject(HttpStatus.OK.value(), "success", "");
  }

  @GetMapping("productsInStore/{id}")
  public ResponseObject findById(@PathVariable("id") long id) {
    List<ProductStoreEntity> productDTOS = productStoreService.getProductInStore(id);
    return new ResponseObject(HttpStatus.OK.value(), "success", productDTOS);
  }
}
