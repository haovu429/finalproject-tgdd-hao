package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.PaymentDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PaymentService {

    ResponseObject save(PaymentDTO paymentDTO);

    ResponseObject update(long id, PaymentDTO paymentDTO);

    ResponseObject delete(long id);

    ResponseObject getAll(Pageable pageable);

    ResponseObject getById(long id);
}
