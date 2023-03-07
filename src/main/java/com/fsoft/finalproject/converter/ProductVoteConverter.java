//package com.fsoft.finalproject.converter;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//import com.fsoft.finalproject.dto.CustomerDTO;
//
//@Component
//public class ProductVoteConverter {
//  ModelMapper mapper = new ModelMapper();
//
//  public ProductVoteDTO toDTO(ProductVoteEntity vote) {
//    ProductVoteDTO voteDTO = new ProductVoteDTO();
//    voteDTO.setId(vote.getId());
//    voteDTO.setCustomer(mapper.map(vote.getCustomerEntity(), CustomerDTO.class));
//    voteDTO.setRate(vote.getRate());
//    voteDTO.setProductCode(vote.getProductEntity().getProductCode());
//    return voteDTO;
//  }
//}
