/** Hao Vu - haovu961@gmail.com - Aug 1, 2022 - 10:20:10 PM */
package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.finalproject.dto.ManufacturerDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.service.ManufacturerService;

@RestController
@RequestMapping(path = "/api/v1")
public class ManufacturerController {

  @Autowired private ManufacturerService manufacturerService;

  @GetMapping("/manufacturer")
  ResponseObject getAll() {
    return manufacturerService.getAll();
  }

  @GetMapping("/manufacturer/{id}")
  ResponseObject getById(@PathVariable Long id) throws CustomException {
    return manufacturerService.getOne(id);
  }

  @PostMapping("/manufacturer/add")
  ResponseObject save(
      @Validated @RequestBody ManufacturerDTO manufacturer, BindingResult bindingResult)
      throws CustomException {
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return manufacturerService.save(manufacturer);
  }

  @PutMapping("/manufacturer/update")
  ResponseObject update(
      @Validated @RequestBody ManufacturerDTO manufacturer, BindingResult bindingResult)
      throws CustomException {
    if (bindingResult.hasErrors()) {
      throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
    }
    return manufacturerService.update(manufacturer);
  }

  @DeleteMapping("/manufacturer/delete/by-id/{id}")
  public ResponseObject delete(@PathVariable Long id) throws CustomException {
    return manufacturerService.delete(id);
  }

  @DeleteMapping("/manufacturer/delete/by-name/{name}")
  public ResponseObject delete(@PathVariable String name) throws CustomException {
    return manufacturerService.delete(name);
  }
}
