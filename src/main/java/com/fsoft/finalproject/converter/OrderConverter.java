//package com.fsoft.finalproject.converter;
//
//import com.fsoft.finalproject.dto.OrderDTO;
//import com.fsoft.finalproject.entity.OrderEntity;
//import com.fsoft.finalproject.repository.CustomerRepository;
//import com.fsoft.finalproject.repository.PaymentRepository;
//import com.fsoft.finalproject.service.CustomerService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.Collectors;
//
//@Component
//public class OrderConverter {
//
//  ModelMapper modelMapper = new ModelMapper();
//
//  @Autowired private CustomerRepository customerRepository;
//
//  @Autowired private PaymentRepository paymentRepository;
//
//  @Autowired private OrderDetailConverter orderDetailConverter;
//
//  public OrderDTO convertToDTO(OrderEntity orderEntity) {
//    OrderDTO orderDTO = modelMapper.map(orderEntity, OrderDTO.class);
//    orderDTO.setOrderCode("ORD" + orderEntity.getId());
//    orderDTO.setOrderDetailDTOs(
//        orderEntity.getOrderDetailEntities().stream()
//            .map(orderDetailConverter::convertToDTO)
//            .collect(Collectors.toList()));
//    return orderDTO;
//  }
//
//  public OrderEntity convertToEntity(OrderDTO orderDTO) {
//    OrderEntity orderEntity = modelMapper.map(orderDTO, OrderEntity.class);
//    orderEntity.setCustomerEntity(customerRepository.findOneById(orderDTO.getCustomerId()));
//    orderEntity.setPaymentEntity(paymentRepository.findOneById(orderDTO.getPaymentId()));
//    //      store waiting
//
//    return orderEntity;
//  }
//}
