/** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 7:55:36 PM */
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

import com.fsoft.finalproject.dto.ProvinceDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.service.ProvinceService;

@RestController
@RequestMapping(path = "/api/v1")
public class ProvinceController {

  @Autowired private ProvinceService provinceService;

  @GetMapping("/province")
  ResponseObject getAll() {
    return provinceService.getAll();
  }

  @GetMapping("/province/{id}")
  ResponseObject getById(@PathVariable Long id) {
    return provinceService.getOne(id);
  }

  @PostMapping("/province/add")
  ResponseObject save(@RequestBody ProvinceDTO province) {
    return provinceService.save(province);
  }

  @PutMapping("/province/update")
  ResponseObject update(@RequestBody ProvinceDTO province) {
    return provinceService.update(province);
  }

  @DeleteMapping("/province/delete/by-id/{id}")
  public ResponseObject delete(@PathVariable Long id) {
    return provinceService.delete(id);
  }

  @DeleteMapping("/province/delete/by-name/{name}")
  public ResponseObject delete(@PathVariable String name) {
    return provinceService.delete(name);
  }

  @DeleteMapping("/province/delete-all")
  public ResponseObject deleteAll() {
    return provinceService.deleteAll();
  }
}
