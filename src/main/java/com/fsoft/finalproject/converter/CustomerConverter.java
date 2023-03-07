//package com.fsoft.finalproject.converter;
//
//import com.fsoft.finalproject.dto.CustomerDTO;
//import com.fsoft.finalproject.entity.CustomerEntity;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomerConverter {
//
//  ModelMapper mapper = new ModelMapper();
//
//  public CustomerDTO toDTO(CustomerEntity customerEntity) {
//    CustomerDTO customer = mapper.map(customerEntity, CustomerDTO.class);
//    return customer;
//  }
//
//  public CustomerEntity toEntity(CustomerDTO customerDTO) {
//    CustomerEntity customerEntity = mapper.map(customerDTO, CustomerEntity.class);
//    return customerEntity;
//  }
//}
