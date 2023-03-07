//package com.fsoft.finalproject.converter;
//
//import com.fsoft.finalproject.dto.OrderDTO;
//import com.fsoft.finalproject.dto.PaymentDTO;
//import com.fsoft.finalproject.entity.OrderEntity;
//import com.fsoft.finalproject.entity.PaymentEntity;
//import com.fsoft.finalproject.repository.PaymentRepository;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class PaymentConverter {
//
//  private final ModelMapper mapper = new ModelMapper();
//
//  @Autowired private OrderConverter orderConverter = new OrderConverter();
//
//  public PaymentDTO toDTO(PaymentEntity paymentEntity) {
//    PaymentDTO payment = mapper.map(paymentEntity, PaymentDTO.class);
//    return payment;
//  }
//
//  public PaymentEntity toEntity(PaymentDTO paymentDTO) {
//    PaymentEntity paymentEntity = mapper.map(paymentDTO, PaymentEntity.class);
//    return paymentEntity;
//  }
//}
