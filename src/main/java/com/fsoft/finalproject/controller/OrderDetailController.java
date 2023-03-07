package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.dto.OrderDetailDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.service.OrderDetailService;
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
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping(path = "/order-details")
    public ResponseObject getAll(@RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.by("id").descending());
        return orderDetailService.getAll(pageable);
    }

    @GetMapping(path = "/order-details/{id}")
    public ResponseObject getById(@PathVariable Long id) throws CustomException {
        return orderDetailService.getById(id);
    }

    @GetMapping(path = "/order-details/order/{id}")
    public ResponseObject getByOrderId(@PathVariable Long id) throws CustomException {
        return orderDetailService.getAllByOrderId(id);
    }

    @GetMapping(path = "/order-details/order/{id}/total-price")
    public ResponseObject getTotalPriceByOrderId(@PathVariable Long id) {
        return orderDetailService.getTotalPriceByOrderId(id);
    }

    @PostMapping(path = "/order-details")
    public ResponseObject save(@Validated @RequestBody OrderDetailDTO orderDetailDTO, BindingResult bindingResult) throws CustomException {
        if(bindingResult.hasErrors()){
            throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
        }
        return orderDetailService.save(orderDetailDTO);
    }

    @PutMapping(path = "/order-details/{id}")
    public ResponseObject update(@PathVariable Long id, @Validated @RequestBody OrderDetailDTO orderDetailDTO,
                                 BindingResult bindingResult) throws CustomException {
        if(bindingResult.hasErrors()){
            throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
        }
        return orderDetailService.update(id, orderDetailDTO);
    }

    @DeleteMapping(path = "/order-details/{id}")
    public ResponseObject delete(@PathVariable Long id) throws CustomException {
        return orderDetailService.delete(id);
    }
}
