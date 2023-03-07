/** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 4:55:05 PM */
package com.fsoft.finalproject.service;

import org.springframework.http.ResponseEntity;

import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.WardDTO;

public interface WardService {
  ResponseObject getOne(long id);

  ResponseObject save(WardDTO ward);

  ResponseObject update(WardDTO ward);

  ResponseObject delete(long id);

  ResponseObject delete(String name);

  ResponseObject deleteAll();

  ResponseObject getAll();

  ResponseObject getByDistrict(long id);

  ResponseObject getByDistrict(String name);

  ResponseObject deleteByDistrict(long id);

  ResponseObject deleteByDistrict(String name);

}
