package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.Cart;
import com.fsoft.finalproject.dto.OrderDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import org.springframework.data.domain.Pageable;

public interface OrderService {
  ResponseObject getAll(Pageable pageable);

  ResponseObject getById(Long id) throws CustomException;

  ResponseObject save(OrderDTO orderDTO);

  ResponseObject update(Long id, OrderDTO orderDTO) throws CustomException;

  ResponseObject delete(Long id) throws CustomException;

  ResponseObject getByCustomerPhone(String phone) throws CustomException;

  ResponseObject saveCartToOder(Cart cart);
}
