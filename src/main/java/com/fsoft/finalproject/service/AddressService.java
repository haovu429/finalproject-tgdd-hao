package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.AddressDTO;
import com.fsoft.finalproject.dto.CustomerAddressDTO;
import com.fsoft.finalproject.dto.NewAddressDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AddressService {
  ResponseObject findById(Long aLong) throws CustomException;

  ResponseObject deleteById(Long id);

  ResponseObject findAll(Pageable pageable);

  ResponseObject findByCustomerId(Long customerId);

  ResponseObject saveCustomerAddress(CustomerAddressDTO dto) throws CustomException;

  ResponseObject saveStoreAddress(AddressDTO dto) throws CustomException;

  ResponseObject updateStoreAddress(long id, AddressDTO dto) throws CustomException;

  ResponseObject updateCustomerAddress(long id, AddressDTO addressDTO) throws CustomException;

  ResponseObject getAllByCustomerPhone(String customerPhone);
}
