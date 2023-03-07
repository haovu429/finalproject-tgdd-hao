/** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 3:34:40 PM */
package com.fsoft.finalproject.service;

import org.springframework.http.ResponseEntity;

import com.fsoft.finalproject.dto.ProvinceDTO;
import com.fsoft.finalproject.dto.ResponseObject;

public interface ProvinceService {

  ResponseObject getOne(long id);

  ResponseObject save(ProvinceDTO province);

  ResponseObject update(ProvinceDTO province);

  ResponseObject delete(long id);

  ResponseObject delete(String name);

  ResponseObject deleteAll();

  ResponseObject getAll();
}
