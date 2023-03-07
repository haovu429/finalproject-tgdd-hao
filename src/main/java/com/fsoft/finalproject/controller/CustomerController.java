package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.dto.CustomerDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.service.CustomerService;
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
public class CustomerController {
    @Autowired
    public CustomerService customerService;


    @GetMapping("/customer/{id}")
    public ResponseObject getById(@PathVariable Long id) throws CustomException {
        return customerService.getById(id);
    }

    @GetMapping(value = "/customer",params = "email")
    public ResponseObject getByEmail(@RequestParam(value = "email",required = false) String email) throws CustomException {
        return customerService.getByEmail(email);
    }

    @GetMapping(value ="/customer",params = "phone")
    public ResponseObject getByPhone(@RequestParam("phone") String phone) throws CustomException {
        return customerService.getByPhone(phone);
    }

    @GetMapping(value = "/customer",params = "page")
    public ResponseObject getAll(@RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.by("id").descending());
        return customerService.getAll(pageable);
    }

    @PostMapping("/customer")
    public ResponseObject save(@Validated @RequestBody CustomerDTO customerDTO,
                               BindingResult bindingResult) throws CustomException {
        if(bindingResult.hasErrors()){
            throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
        }
        return customerService.save(customerDTO);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseObject delete(@PathVariable Long id) throws CustomException {
        return customerService.delete(id);
    }

    @PutMapping("/customer/{id}")
    public ResponseObject update(@PathVariable Long id,@Validated @RequestBody CustomerDTO customerDTO,
                                 BindingResult bindingResult) throws CustomException {
        if(bindingResult.hasErrors()){
            throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
        }
        return customerService.update(id, customerDTO);
    }

    @PostMapping("/customer/login")
    public ResponseObject login(@RequestParam String email){
        return customerService.sendOTPLogin(email);
    }

    @PostMapping("/customer/validate-otp/{otp}")
    public ResponseObject validate(@PathVariable String otp){
        return customerService.validateOtp(otp);
    }

}
