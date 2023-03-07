//package com.fsoft.finalproject.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.fsoft.finalproject.dto.ResponseObject;
//import com.fsoft.finalproject.service.ProductVoteService;
//
//@RestController
//@RequestMapping(path = "/api/v1")
//public class ProductVoteController {
//
//	@Autowired
//	private ProductVoteService productVoteService;
//
//	@PostMapping("/vote/{code}/{phone}")
//	ResponseObject save(@PathVariable("code") String code,@PathVariable("phone") String phone,@RequestBody ObjectNode objectNode){
//		float rate = objectNode.get("rate").floatValue();
//		return productVoteService.save(code, phone, rate);
//	}
//
//	@GetMapping("/voteByProduct/{id}")
//	ResponseObject getVoteByProduct(@PathVariable String id){
//		return productVoteService.getVoteByProduct(id);
//	}
//
//	@PutMapping("/vote/{code}/{phone}")
//	ResponseObject update(@PathVariable("code") String code,@PathVariable("phone") String phone,@RequestBody ObjectNode objectNode){
//		float rate = objectNode.get("rate").floatValue();
//		return productVoteService.update(code, phone, rate);
//	}
//
//	@DeleteMapping("/vote/{code}/{phone}")
//	ResponseObject deleteCustomer(@PathVariable("code") String code,@PathVariable("phone") String phone){
//		return productVoteService.delete(code, phone);
//	}
//
//}
