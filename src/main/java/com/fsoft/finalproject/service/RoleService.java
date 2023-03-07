package com.fsoft.finalproject.service;

import org.springframework.http.ResponseEntity;

import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.RoleDTO;

public interface RoleService {
	ResponseObject save(RoleDTO role);

	ResponseObject update(RoleDTO role);

	ResponseObject delete(String code);

	ResponseObject getAll();
}
