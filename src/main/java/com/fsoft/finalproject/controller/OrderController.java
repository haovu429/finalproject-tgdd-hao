package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.dto.Cart;
import com.fsoft.finalproject.dto.OrderDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class OrderController {

  @Autowired
  public OrderService orderService;

  @GetMapping("/order")
  public ResponseObject getAll(@RequestParam("page") Optional<Integer> page) {
    Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.by("id").descending());
    return orderService.getAll(pageable);
  }

  @GetMapping("/order/{id}")
  public ResponseObject getById(@PathVariable Long id) throws CustomException {
    return orderService.getById(id);
  }

  @PostMapping("/order")
  public ResponseObject save(@Validated @RequestBody OrderDTO orderDTO, BindingResult bindingResult) throws CustomException {
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return orderService.save(orderDTO);
  }

  @PostMapping("/order/cart")
  public ResponseObject saveCart(@Validated @RequestBody Cart cart, BindingResult bindingResult) throws CustomException {
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return orderService.saveCartToOder(cart);
  }


  @PutMapping("/order/{id}")
  public ResponseObject update(@Validated @PathVariable Long id, @RequestBody OrderDTO orderDTO, BindingResult bindingResult) throws CustomException {
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return orderService.update(id, orderDTO);
  }

  @DeleteMapping("/order/{id}")
  public ResponseObject delete(@PathVariable Long id) throws CustomException {
    return orderService.delete(id);
  }

  @GetMapping("/order/customer/{customerPhone}")
  public ResponseObject getByCustomerPhone(@PathVariable String customerPhone) {
    return orderService.getByCustomerPhone(customerPhone);
  }
}
