package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.dto.BillDetailDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.service.BillDetailService;
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
public class BillDetailController {

    @Autowired
    private BillDetailService billDetailService;

    @GetMapping("/billdetail/{id}")
    public ResponseObject getById(@PathVariable Long id) throws CustomException {
        return billDetailService.getById(id);
    }

    @GetMapping("billdetail")
    public ResponseObject getAll(@RequestParam("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.by("id").descending());
        return billDetailService.getAll(pageable);
    }

    @GetMapping("/billdetail/bill/{id}")
    public ResponseObject getBillDetailByBillId(@PathVariable Long id) throws CustomException {
        return billDetailService.getBillDetailByBillId(id);
    }

    @PostMapping("/billdetail")
    public ResponseObject save(@Validated  @RequestBody BillDetailDTO billDetailDTO,
                               BindingResult bindingResult) throws CustomException {
        if(bindingResult.hasErrors()){
            throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
        }
        return new ResponseObject(billDetailService.save(billDetailDTO));
    }

    @DeleteMapping("/billdetail/{id}")
    public ResponseObject delete(@PathVariable Long id) throws CustomException {
        return billDetailService.delete(id);
    }

    @PutMapping("/billdetail/{id}")
    public ResponseObject update(@Validated @PathVariable Long id,
                                 @RequestBody BillDetailDTO billDetailDTO,
                                 BindingResult bindingResult) throws CustomException {
        if(bindingResult.hasErrors()){
            throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
        }
        return billDetailService.update(id, billDetailDTO);
    }

}
