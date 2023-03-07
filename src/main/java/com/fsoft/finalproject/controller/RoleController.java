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
import com.fsoft.finalproject.dto.RoleDTO;
import com.fsoft.finalproject.service.RoleService;

@RestController
@RequestMapping(path = "/api/v1")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("/role")
	ResponseObject getAll() {
		return roleService.getAll();
	}

	@PostMapping("/role")
	ResponseObject save(@RequestBody RoleDTO role) {
		return roleService.save(role);

	}

	@PutMapping("/role")
	ResponseObject update(@RequestBody RoleDTO role) {
		return roleService.update(role);

	}

	@DeleteMapping("/role/{code}")
	ResponseObject delete(@PathVariable String code) {
		return roleService.delete(code);

	}

}
