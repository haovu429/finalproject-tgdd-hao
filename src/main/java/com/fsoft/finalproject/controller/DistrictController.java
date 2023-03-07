/**
 * Hao Vu - haovu961@gmail.com - Jul 31, 2022 - 3:15:13 PM
 */
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

import com.fsoft.finalproject.dto.DistrictDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.service.DistrictService;

@RestController
@RequestMapping(path = "/api/v1")
public class DistrictController {

	@Autowired
	private DistrictService districtService;

	@GetMapping("/district")
	ResponseObject getAll() {
		return districtService.getAll();
	}

	@GetMapping("/district/{id}")
	ResponseObject getById(@PathVariable Long id) throws CustomException {
		return districtService.getOne(id);
	}

	@GetMapping("/district/get-by-province-id/{id}")
	ResponseObject getByProvinceId(@PathVariable Long id) throws CustomException {
		return districtService.getByProvince(id);
	}

	@GetMapping("/district/get-by-province-name/{name}")
	ResponseObject getByProvinceName(@PathVariable String name) throws CustomException {
		return districtService.getByProvince(name);
	}

	@PostMapping("/district/add")
	ResponseObject save(@Validated  @RequestBody DistrictDTO district, BindingResult bindingResult) throws CustomException {
		if(bindingResult.hasErrors()){
			throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
		}
		return districtService.save(district);

	}

	@PutMapping("/district/update")
	ResponseObject update(@Validated @RequestBody DistrictDTO district, BindingResult bindingResult) throws CustomException {
		if(bindingResult.hasErrors()){
			throw new CustomException(bindingResult.getFieldError().getDefaultMessage());
		}
		return districtService.update(district);

	}

	@DeleteMapping("/district/delete/by-id/{id}")
	public ResponseObject delete(@PathVariable Long id) throws CustomException {
		return districtService.delete(id);
	}

	@DeleteMapping("/district/delete/by-name/{name}")
	public ResponseObject delete(@PathVariable String name) throws CustomException {
		return districtService.delete(name);
	}

	@DeleteMapping("/district/delete/by-province-id/{id}")
	public ResponseObject deleteByProvincetId(@PathVariable Long id) throws CustomException {
		return districtService.deleteByProvince(id);
	}

	@DeleteMapping("/district/delete/by-province-name/{name}")
	public ResponseObject deleteByProvinceName(@PathVariable String name) throws CustomException {
		return districtService.deleteByProvince(name);
	}

}
