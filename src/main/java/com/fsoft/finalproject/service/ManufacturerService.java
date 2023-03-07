/** Hao Vu - haovu961@gmail.com - Aug 1, 2022 - 9:38:29 PM */
package com.fsoft.finalproject.service;

import com.fsoft.finalproject.exception.CustomException;

import com.fsoft.finalproject.dto.ManufacturerDTO;
import com.fsoft.finalproject.dto.ResponseObject;

public interface ManufacturerService {

  ResponseObject getOne(long id) throws CustomException;

  ResponseObject save(ManufacturerDTO manufacturerDTO) throws CustomException;

  ResponseObject update(ManufacturerDTO manufacturerDTO) throws CustomException;

  ResponseObject delete(long id) throws CustomException;

  ResponseObject delete(String name) throws CustomException;

  ResponseObject getAll();
}
