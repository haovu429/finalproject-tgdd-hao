package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.dto.*;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.service.Impl.PhoneServiceImpl;
import com.fsoft.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class PhoneController {

  @Autowired PhoneServiceImpl phoneService;

  @Autowired ProductService productService;

  /**
   * find all phones
   *
   * @param page number
   * @return list phoneDTO
   */
  @GetMapping("phones")
  @ResponseBody
  public ResponseObject findAll(@RequestParam("page") Optional<Integer> page) {
    Pageable pageable = PageRequest.of(page.orElse(0), 5);
    Page<PhoneDTO> phones = phoneService.findAll(pageable);
    return new ResponseObject(HttpStatus.OK.value(), "success", phones);
  }

  @PostMapping("phone")
  public ResponseObject save(
      @Validated @RequestBody CreatePhoneDTO phoneDTO, BindingResult bindingResult) {
    // check manufacturer == update later
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return phoneService.savePhone(phoneDTO);
  }

  @DeleteMapping("phone/{id}")
  public ResponseObject delete(@PathVariable("id") long id) {
    boolean res = phoneService.deletePhone(id);
    if (res) {
      return new ResponseObject(HttpStatus.OK.value(), "success", null);
    } else {
      throw new RuntimeException("Not found phone with id: " + id);
    }
  }

  @PutMapping("phone/{id}")
  public ResponseObject update(
      @RequestBody @Validated PhoneDTO phoneDTO,
      @PathVariable("id") long id,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new RuntimeException(bindingResult.getFieldError().toString());
    }
    Optional<PhoneDTO> result = phoneService.updatePhone(id, phoneDTO);
    if (!result.isPresent()) {
      throw new RuntimeException("Not found phone with id: " + id);
    }
    return new ResponseObject(HttpStatus.OK.value(), "success", result.get());
  }

  @PostMapping("/phone/filter")
  public ResponseObject filterPhone(@RequestBody ProductPhoneFilterDTO filterDTO) {
    return productService.filterPhone(
        filterDTO.getManufacturer(),
        filterDTO.getMinPrice(),
        filterDTO.getMaxPrice(),
        filterDTO.getScreen(),
        filterDTO.getCpu(),
        filterDTO.getRam(),
        filterDTO.getMemory(),
        filterDTO.getOther(),
        filterDTO.getNoOfRecords(),
        filterDTO.getPageIndex(),
        filterDTO.isSortByPrice());
  }
}
