package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.BillDetailDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import org.springframework.data.domain.Pageable;

public interface BillDetailService {

    ResponseObject getBillDetailByBillId(Long billId) throws CustomException;

    ResponseObject save(BillDetailDTO billDetailDTO) throws CustomException;

    ResponseObject update(Long id, BillDetailDTO billDetailDTO) throws CustomException;

    ResponseObject delete(Long id) throws CustomException;

    ResponseObject getAll(Pageable pageable);

    ResponseObject getById(Long id) throws CustomException;

}
