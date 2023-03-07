package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.PaymentDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.PaymentEntity;
import com.fsoft.finalproject.repository.PaymentRepository;
import com.fsoft.finalproject.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

  @Autowired private PaymentRepository paymentRepository;

  @Autowired private Converter converter;

  @Override
  public ResponseObject save(PaymentDTO paymentDTO) {
    PaymentEntity paymentEntity = converter.toEntity(paymentDTO);
    paymentRepository.save(paymentEntity);
    return new ResponseObject(converter.toDTO(paymentEntity));
  }

  @Override
  public ResponseObject update(long id, PaymentDTO paymentDTO) {
    PaymentEntity paymentEntityUpdate = converter.toEntity(paymentDTO);

    PaymentEntity paymentEntity = paymentRepository.findOneById(id);

    if (paymentEntity == null) {
      throw new RuntimeException("Payment not found");
    } else {
      paymentEntityUpdate.setId(paymentEntity.getId());
      paymentEntity = paymentRepository.save(paymentEntityUpdate);
      return new ResponseObject(converter.toDTO(paymentEntity));
    }
  }

  @Override
  public ResponseObject delete(long id) {
    boolean exist = paymentRepository.existsById(id);
    if (!exist) {
      throw new RuntimeException("Payment not found");
    }
    paymentRepository.deleteById(id);
    return new ResponseObject(200, "Delete success", "");
  }

  @Override
  public ResponseObject getAll(Pageable pageable) {
    List<PaymentDTO> paymentDTOs =
        paymentRepository.findAll(pageable).stream()
            .map(converter::toDTO)
            .collect(Collectors.toList());

    return new ResponseObject(paymentDTOs);
  }

  @Override
  public ResponseObject getById(long id) {
    boolean exist = paymentRepository.existsById(id);
    if (!exist) {
      throw new RuntimeException("Payment not found");
    }
    PaymentEntity paymentEntity = paymentRepository.findOneById(id);
    return new ResponseObject(converter.toDTO(paymentEntity));
  }
}
