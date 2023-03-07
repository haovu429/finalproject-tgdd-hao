package com.fsoft.finalproject.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.RoleDTO;
import com.fsoft.finalproject.entity.RoleEntity;
import com.fsoft.finalproject.repository.RoleRepository;
import com.fsoft.finalproject.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	ModelMapper mapper = new ModelMapper();

	@Override
	public ResponseObject save(RoleDTO role) {
		RoleEntity roleEntity = roleRepository.findOneByCode(role.getCode());
		if (roleEntity != null) {
			throw new RuntimeException("Role already exists");
		} else {
			roleEntity = mapper.map(role, RoleEntity.class);
			roleEntity = roleRepository.save(roleEntity);
			return new ResponseObject(HttpStatus.OK.value(), "Create role successfully",
					mapper.map(roleEntity, RoleDTO.class));
		}
	}

	@Override
	public ResponseObject update(RoleDTO roleDTO) {
		RoleEntity newRole = roleRepository.findById(roleDTO.getCode()).map(role -> {
			role.setName(roleDTO.getName());
			return roleRepository.save(role);
		}).orElse(null);
		if (newRole == null) {
			throw new RuntimeException("Role not found");
		} else {
			return new ResponseObject(HttpStatus.OK.value(), "Update role successfully",
					mapper.map(newRole, RoleDTO.class));
		}
	}

	@Override
	public ResponseObject delete(String code) {
		boolean exists = roleRepository.existsById(code);
		if (exists) {
			roleRepository.deleteById(code);
			return new ResponseObject(HttpStatus.OK.value(), "Delete role successfully", "");
		} else {
			throw new RuntimeException("Role not found");
		}
	}

	@Override
	public ResponseObject getAll() {
		// TODO Auto-generated method stub
		List<RoleEntity> roleEntitys = roleRepository.findAll();
		List<RoleDTO> roleDTOs = new ArrayList<>();

		// Cast List Entity --> List DTO
		for (RoleEntity i : roleEntitys) {
			RoleDTO role = mapper.map(i, RoleDTO.class);
			roleDTOs.add(role);
		}
		return new ResponseObject(HttpStatus.OK.value(), "Get all role successfully", roleDTOs);
	}

}
