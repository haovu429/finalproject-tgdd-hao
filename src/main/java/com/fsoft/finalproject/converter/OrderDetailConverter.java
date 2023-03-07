//package com.fsoft.finalproject.converter;
//
//import com.fsoft.finalproject.dto.OrderDetailDTO;
//import com.fsoft.finalproject.dto.ProductDTO;
//import com.fsoft.finalproject.entity.OrderDetailEntity;
//import com.fsoft.finalproject.entity.ProductEntity;
//import com.fsoft.finalproject.repository.OrderRepository;
//import com.fsoft.finalproject.repository.ProductRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OrderDetailConverter {
//  ModelMapper modelMapper = new ModelMapper();
//
//  @Autowired private OrderRepository orderRepository;
//
//  @Autowired private ProductRepository productRepository;
//
//  public OrderDetailDTO convertToDTO(OrderDetailEntity orderDetailEntity) {
//    OrderDetailDTO orderDetailDTO = modelMapper.map(orderDetailEntity, OrderDetailDTO.class);
//    ProductDTO productDTO = modelMapper.map(orderDetailEntity.getProductEntity(), ProductDTO.class);
//    orderDetailDTO.setProductDTO(productDTO);
//    return orderDetailDTO;
//  }
//
//  public OrderDetailEntity convertToEntity(OrderDetailDTO orderDetailDTO) {
//    OrderDetailEntity orderDetailEntity = modelMapper.map(orderDetailDTO, OrderDetailEntity.class);
//    orderDetailEntity.setOrderEntity(orderRepository.findOneById(orderDetailDTO.getOrderId()));
//    //        Product waiting for implement
//    orderDetailEntity.setProductEntity(
//        productRepository.findOneById(orderDetailDTO.getProductDTO().getId()));
//    return orderDetailEntity;
//  }
//}
