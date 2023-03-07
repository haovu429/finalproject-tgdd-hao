package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.AddressDTO;
import com.fsoft.finalproject.dto.CustomerAddressDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class AddressController {

  @Autowired
  AddressService addressService;

  @Autowired
  Converter converter;

  @GetMapping("addresses")
  @ResponseBody
  public ResponseObject getAll(@RequestParam("page") Optional<Integer> page) {
    Pageable pageable = PageRequest.of(page.orElse(0), 5);
    return addressService.findAll(pageable);
  }

  @GetMapping("address")
  @ResponseBody
  public ResponseObject findById(@RequestParam("id") long id) throws CustomException {
    return addressService.findById(id);
  }

  @GetMapping(value = "addresses", params = "customerId")
  @ResponseBody
  public ResponseObject findCustomerId(@RequestParam("customerId") long customerId) throws CustomException {
    return addressService.findByCustomerId(customerId);
  }

  @DeleteMapping("address/{id}")
  @ResponseBody
  public ResponseObject deleteById(@PathVariable("id") long id) {
    return addressService.deleteById(id);
  }

  @PostMapping("store-address")
  public ResponseObject saveStoreAddress(@Validated @RequestBody AddressDTO addressDTO,
                                         BindingResult bindingResult) throws CustomException {
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return addressService.saveStoreAddress(addressDTO);
  }

  @PostMapping("customer-address")
  public ResponseObject saveCustomerAddress(@Validated @RequestBody CustomerAddressDTO addressDTO,
                                            BindingResult bindingResult) throws CustomException {
    if (addressDTO == null) {
      throw new CustomException("data is null");
    }
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return addressService.saveCustomerAddress(addressDTO);
  }

  @PutMapping("customer-address/{id}")
  public ResponseObject updateCustomerAddress(@PathVariable("id") long id,
                                              @Validated @RequestBody AddressDTO dto,
                                              BindingResult bindingResult) throws CustomException {
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return addressService.updateCustomerAddress(id, dto);
  }


  @PutMapping("store-address/{id}")
  public ResponseObject updateStoreAddress(@PathVariable("id") long id,
                                           @Validated @RequestBody AddressDTO dto,
                                           BindingResult bindingResult) throws CustomException {
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return addressService.updateStoreAddress(id, dto);
  }

  @GetMapping("/address/customer/{customerPhone}")
  public ResponseObject getByCustomerPhone(@PathVariable String customerPhone) {
    return addressService.getAllByCustomerPhone(customerPhone);
  }

}
