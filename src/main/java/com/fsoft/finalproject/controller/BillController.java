package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.dto.BillDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.service.BillService;
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
public class BillController {

    @Autowired
    public BillService billService;

    @GetMapping("/bill")
    public ResponseObject getAll(@RequestParam("page")Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.by("id").descending());
        return billService.getAll(pageable);
    }

    @GetMapping("/bill/{id}")
    public ResponseObject getById(@PathVariable Long id) {
        return billService.getById(id);
    }

    @GetMapping("/bill/customer/{customerPhone}")
    public ResponseObject getByCustomerPhone(@PathVariable String customerPhone) {
        return billService.getAllByCustomerPhone(customerPhone);
    }

    @GetMapping("/bill/max-total-price")
    public ResponseObject getBillHasMaxTotalPrice() {
        return billService.getBillHasMaxTotalPrice();
    }

    @PostMapping("/bill")
    public ResponseObject save(@RequestBody @Validated BillDTO billDTO, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            return billService.save(billDTO);
        }else{
            throw new RuntimeException(bindingResult.getFieldError().toString());
        }
    }

    @DeleteMapping("/bill/{id}")
    public ResponseObject delete(@PathVariable Long id) {
        return billService.delete(id);
    }

    @PutMapping("/bill/{id}")
    public ResponseObject update(@PathVariable Long id, @RequestBody @Validated BillDTO billDTO, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            return billService.update(id, billDTO);
        }else{
            throw new RuntimeException(bindingResult.getFieldError().toString());
        }
    }
}
