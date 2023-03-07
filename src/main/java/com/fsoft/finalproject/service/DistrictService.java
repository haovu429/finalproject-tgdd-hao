/** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 5:05:29 PM */
package com.fsoft.finalproject.service;

import com.fsoft.finalproject.exception.CustomException;

import com.fsoft.finalproject.dto.DistrictDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface DistrictService {

	ResponseObject getOne(long id) throws CustomException;

	ResponseObject save(DistrictDTO province) throws CustomException;

	ResponseObject update(DistrictDTO province) throws CustomException;

	ResponseObject delete(long id) throws CustomException;

	ResponseObject delete(String name) throws CustomException;

    ResponseObject deleteAll() throws CustomException;

	ResponseObject getAll();

	ResponseObject getByProvince(long id) throws CustomException;

	ResponseObject getByProvince(String name) throws CustomException;

	ResponseObject deleteByProvince(long id) throws CustomException;

	ResponseObject deleteByProvince(String name) throws CustomException;

}
