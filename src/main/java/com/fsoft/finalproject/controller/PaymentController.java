package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.dto.PaymentDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.service.PaymentService;
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
public class PaymentController {

    @Autowired
    public PaymentService paymentService;

    @GetMapping("/payment")
    public ResponseObject getAll(@RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.by("id").descending());
        return paymentService.getAll(pageable);
    }

    @GetMapping("/payment/{id}")
    public ResponseObject getById(@PathVariable Long id) {
        return paymentService.getById(id);
    }

    @PostMapping("/payment")
    public ResponseObject save(@RequestBody @Validated PaymentDTO paymentDTO, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            return paymentService.save(paymentDTO);
        }else {
            throw new RuntimeException(bindingResult.getFieldError().toString());
        }
    }

    @DeleteMapping("/payment/{id}")
    public ResponseObject delete(@PathVariable Long id) {
        return paymentService.delete(id);
    }

    @PutMapping("/payment/{id}")
    public ResponseObject update(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        return paymentService.update(id, paymentDTO);
    }

}
