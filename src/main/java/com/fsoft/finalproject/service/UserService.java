package com.fsoft.finalproject.service;

import org.springframework.http.ResponseEntity;

import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.UserDTO;



public interface UserService {
	ResponseObject save(UserDTO userDTO );
	ResponseObject update(long id,UserDTO userDTO);
	ResponseObject delete(long id);
	ResponseObject getAll();
	ResponseObject getUserByAccount(String email,String password);
	
}
