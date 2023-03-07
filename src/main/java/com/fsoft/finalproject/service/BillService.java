package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.BillDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BillService {

    ResponseObject save(BillDTO billDTO);

    ResponseObject update(long id, BillDTO billDTO);

    ResponseObject delete(long id);

    ResponseObject getAll(Pageable pageable);

    ResponseObject getById(long id);

    ResponseObject getAllByCustomerPhone(String phone);

    ResponseObject getBillHasMaxTotalPrice();
}
