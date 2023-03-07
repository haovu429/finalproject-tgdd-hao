/** Hao Vu - haovu961@gmail.com - Jul 31, 2022 - 3:15:24 PM */
package com.fsoft.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.WardDTO;
import com.fsoft.finalproject.service.WardService;

@RestController
@RequestMapping(path = "/api/v1")
public class WardController {

  @Autowired private WardService wardService;

  @GetMapping("/ward")
  ResponseObject getAll() {
    return wardService.getAll();
  }

  @GetMapping("/ward/{id}")
  ResponseObject getById(@PathVariable Long id) {
    return wardService.getOne(id);
  }

  @GetMapping("/ward/by-district-id/{id}")
  ResponseObject getByDistricteId(@PathVariable Long id) {
    return wardService.getByDistrict(id);
  }

  @GetMapping("/ward/by-district-name/{name}")
  ResponseObject getByDistricteName(@PathVariable String name) {
    return wardService.getByDistrict(name);
  }

  @PostMapping("/ward/add")
  ResponseObject save(@RequestBody WardDTO district) {
    return wardService.save(district);
  }

  @PutMapping("/ward/update")
  ResponseObject update(@RequestBody WardDTO district) {
    return wardService.update(district);
  }

  @DeleteMapping("/ward/delete/by-id/{id}")
  public ResponseObject delete(@PathVariable Long id) {
    return wardService.delete(id);
  }

  @DeleteMapping("/ward/delete/by-name/{name}")
  public ResponseObject delete(@PathVariable String name) {
    return wardService.delete(name);
  }

  @DeleteMapping("/ward/delete/by-district-id/{id}")
  public ResponseObject deleteByDistrictId(@PathVariable Long id) {
    return wardService.deleteByDistrict(id);
  }

  @DeleteMapping("/ward/delete/by-district-name/{name}")
  public ResponseObject deleteByDistrictName(@PathVariable String name) {
    return wardService.deleteByDistrict(name);
  }

  @DeleteMapping("/ward/delete-all")
  public ResponseObject deleteAll() {
    return wardService.deleteAll();
  }
}
