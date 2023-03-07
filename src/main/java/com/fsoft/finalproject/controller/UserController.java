package com.fsoft.finalproject.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.RoleDTO;
import com.fsoft.finalproject.dto.UserDTO;
import com.fsoft.finalproject.service.UserService;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

  @Autowired
  public UserService userService;

  @GetMapping("/user")
  public ResponseObject getAll() {
    return userService.getAll();
  }

  @PostMapping("/user")
  public ResponseObject save(@RequestBody UserDTO user) {
    return userService.save(user);
  }

  @DeleteMapping("/user/{id}")
  public ResponseObject delete(@PathVariable Long id) {
    return userService.delete(id);
  }

  @PutMapping("/user/{id}")
  public ResponseObject update(@PathVariable Long id, @RequestBody UserDTO user) {
    return userService.update(id, user);
  }

  @GetMapping("/test")
  public String update() {
    return "OK";
  }
	
}
