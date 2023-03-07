package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.CustomerDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    ResponseObject getAll(Pageable pageable);

    ResponseObject getById(Long id) throws CustomException;

    ResponseObject save(CustomerDTO customerDTO) throws CustomException;

    ResponseObject update(Long id, CustomerDTO customerDTO) throws CustomException;

    ResponseObject delete(Long id) throws CustomException;

    ResponseObject getByEmail(String email) throws CustomException;

    ResponseObject getByPhone(String phone) throws CustomException;

    ResponseObject sendOTPLogin(String mail);

    ResponseObject validateOtp(String otp);
}
