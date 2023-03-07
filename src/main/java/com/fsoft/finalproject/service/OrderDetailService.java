package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.OrderDetailDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import org.springframework.data.domain.Pageable;


public interface OrderDetailService {

    ResponseObject getAll(Pageable pageable);

    ResponseObject getById(Long id) throws CustomException;

    ResponseObject save(OrderDetailDTO orderDetailDTO);

    ResponseObject update(Long id, OrderDetailDTO orderDetailDTO) throws CustomException;

    ResponseObject delete(Long id) throws CustomException;

    ResponseObject getAllByOrderId(Long orderId) throws CustomException;

    ResponseObject getTotalPriceByOrderId(Long orderId);
}
