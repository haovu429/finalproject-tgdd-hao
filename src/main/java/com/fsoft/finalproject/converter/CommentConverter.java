//package com.fsoft.finalproject.converter;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.fsoft.finalproject.dto.VoteDTO;
//import com.fsoft.finalproject.entity.VoteEntity;
//import com.fsoft.finalproject.entity.ProductEntity;
//import com.fsoft.finalproject.repository.ProductRepository;
//
//@Component
//public class CommentConverter {
//
//  ModelMapper mapper = new ModelMapper();
//
//  @Autowired private ProductRepository productRepository;
//
//  public VoteEntity toEntity(VoteDTO comment) {
//    VoteEntity voteEntity = new VoteEntity();
//    voteEntity = mapper.map(comment, VoteEntity.class);
//    ProductEntity product = productRepository.getProductByCode(comment.getProductCode());
//    voteEntity.setProductEntity(product);
//
//    return voteEntity;
//  }
//
//  public VoteDTO toDTO(VoteEntity comment) {
//    VoteDTO voteDTO = new VoteDTO();
//    voteDTO = mapper.map(comment, VoteDTO.class);
//    voteDTO.setProductCode(comment.getProductEntity().getProductCode());
//
//    return voteDTO;
//  }
//}
