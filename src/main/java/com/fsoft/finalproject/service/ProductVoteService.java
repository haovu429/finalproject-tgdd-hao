package com.fsoft.finalproject.service;

import org.springframework.http.ResponseEntity;

import com.fsoft.finalproject.dto.ResponseObject;

public interface ProductVoteService {

  ResponseObject save(String code, String phone, float rate);

  ResponseObject update(String code, String phone, float rate);

  ResponseObject delete(String code, String phone);

  ResponseObject getVoteByProduct(String code);
}
