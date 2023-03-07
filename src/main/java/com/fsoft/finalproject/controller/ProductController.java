/** Hao Vu - haovu961@gmail.com - Aug 2, 2022 - 2:07:33 PM */
package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.ItemInCart;
import com.fsoft.finalproject.dto.ProductDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.repository.impl.FilterProductRepositoryImpl;
import com.fsoft.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class ProductController {

  @Autowired private Converter converter;

  @Autowired private ProductService productService;

  @Autowired private FilterProductRepositoryImpl filterProductRepository;

  @GetMapping("/product")
  ResponseObject getAll() {
    return productService.getAll();
  }

  @GetMapping("/product/{id}")
  ResponseObject getById(@PathVariable Long id) {
    return productService.getOne(id);
  }

  @GetMapping("/productInCart")
  ResponseObject getProductInCart(@RequestBody List<ItemInCart> listItemInCart) {
    return productService.getListProductByListItemInCart(listItemInCart);
  }

  @PostMapping("/product/add")
  ResponseObject save(@RequestBody @Validated ProductDTO product, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new RuntimeException(bindingResult.getFieldError().toString());
    }
    Optional<ProductDTO> productDTO = productService.save(product);
    if (productDTO.isPresent()) {
      return new ResponseObject(
          HttpStatus.OK.value(), "Create product successfully", productDTO.get());
    } else {
      throw new RuntimeException("Add product failed");
    }
  }

  @PutMapping("/product/update")
  ResponseObject update(@RequestBody @Validated ProductDTO product, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new RuntimeException(bindingResult.getFieldError().toString());
    }
    return productService.update(product);
  }

  @DeleteMapping("/product/delete/by-id/{id}")
  ResponseObject delete(@PathVariable Long id) {
    return productService.delete(id);
  }

  @DeleteMapping("/product/delete/by-product-code/{productCode}")
  ResponseObject deleteByProductCode(@PathVariable String productCode) {
    return productService.deleteByProductCode(productCode);
  }

  @GetMapping("/product/rate-greater/{rate}")
  ResponseObject getProductRateGreater(@PathVariable double rate) {
    return productService.getProductRateGreater(rate);
  }

  @GetMapping("/product/rate-less/{rate}")
  ResponseObject getProductRateLess(@PathVariable double rate) {
    return productService.getProductRateLess(rate);
  }

  @GetMapping("/product/get-by-manufacturer-id/{id}")
  ResponseObject getByManufacturer(@PathVariable long id) {
    return productService.getByManufacturer(id);
  }

  @GetMapping("/product/get-by-manufacturer-name/{name}")
  ResponseObject getByManufacturer(@PathVariable String name) {
    return productService.getByManufacturer(name);
  }

  @GetMapping("/product/contain-name/{name}")
  ResponseObject getProductContainName(@PathVariable String name) {
    return productService.getProductContainName(name);
  }

  @GetMapping("/product/product-amount-manufacturer-id/{id}")
  ResponseObject getProductAmountByManufacturer(@PathVariable long id) {
    return productService.getProductAmountByManufacturer(id);
  }

  @GetMapping("/product/product-amount-manufacturer-name/{name}")
  ResponseObject getProductAmountByManufacturer(@PathVariable String name) {
    return productService.getProductAmountByManufacturer(name);
  }

  @GetMapping("/product/total-amount-by-id/{id}")
  ResponseObject getTotalAmountById(@PathVariable long id) {
    return productService.getTotalAmountById(id);
  }

  @GetMapping("/product/get-rate/{id}")
  ResponseObject getAvgRate(@PathVariable long id) {
    return productService.getProductRate(id);
  }

  @GetMapping("/product/price/greater/{price}")
  ResponseObject getProductPriceGreater(@PathVariable int price) {
    return productService.getProductPriceGreater(price);
  }

  @GetMapping("/product/price/less/{price}")
  ResponseObject getProductPriceLess(@PathVariable int price) {
    return productService.getProductPriceLess(price);
  }

  @GetMapping("/product/price/between/{start}/{end}")
  ResponseObject getProductPriceBetween(@PathVariable int start, @PathVariable int end) {
    return productService.getProductPriceBetween(start, end);
  }

  @GetMapping("/product/configuration-product/{id}")
  ResponseObject getCon(@PathVariable long id) {
    return productService.getConfigurationProduct(id);
  }

  @GetMapping("product/promotion/{id}")
  ResponseObject getAllProductsByPromotion(
      @PathVariable("id") long id, @RequestParam("page") Optional<Integer> page) {
    return productService.getProductsByPromotion(id, page);
  }

  @GetMapping("product/manufacturer/{id}")
  ResponseObject getAllProductsByManufacture(
      @PathVariable("id") long id, @RequestParam("page") Optional<Integer> page) {
    return productService.getProductsbyManufacture(id, page);
  }

  @GetMapping("product/category")
  ResponseObject getAllByCategory(
      @RequestParam("c") String c, @RequestParam("page") Optional<Integer> page) {
    return productService.getProductsByCategory(c, page);
  }

  @GetMapping("product/top-sales")
  public ResponseObject getTopSales() {
    return productService.getTopSales();
  }

  @GetMapping("product/filter")
  public ResponseObject filterProduct(@RequestParam Map<String, String> conditions) {
    return filterProductRepository.filterProduct(conditions);
  }

  @PostMapping("/product/{id}/addPromotion")
  ResponseObject addPromotion(@RequestBody List<String> promotionCodes, @PathVariable Long id) {
    return productService.addListPromotionForProduct(promotionCodes, id);
  }
}
