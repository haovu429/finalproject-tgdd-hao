package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.OrderDetailDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.OrderDetailEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.repository.OrderDetailRespository;
import com.fsoft.finalproject.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

   @Autowired
   private OrderDetailRespository orderDetailRespository;

    @Autowired
    private Converter converter;

    @Override
    public ResponseObject getAll(Pageable pageable) {
        List<OrderDetailDTO> orderDetailDTOS = orderDetailRespository.findAll(pageable)
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
        return new ResponseObject( orderDetailDTOS);
    }

    @Override
    public ResponseObject getById(Long id) throws CustomException {
        OrderDetailEntity orderDetailEntity = orderDetailRespository.findOneById(id);
        if (orderDetailEntity == null) {
            throw new CustomException("Order detail not found with id: " + id);
        } else {
            return new ResponseObject( converter.toDTO(orderDetailEntity));
        }
    }

    @Override
    public ResponseObject save(OrderDetailDTO orderDetailDTO) {
        OrderDetailEntity orderDetailEntity = converter.toEntity(orderDetailDTO);
        orderDetailEntity = orderDetailRespository.save(orderDetailEntity);
        return new ResponseObject( converter.toDTO(orderDetailEntity));
    }

    @Override
    public ResponseObject update(Long id, OrderDetailDTO orderDetailDTO) throws CustomException {
        OrderDetailEntity orderDetailUpdate = converter.toEntity(orderDetailDTO);

        OrderDetailEntity orderDetailEntity = orderDetailRespository.findOneById(id);
        if (orderDetailEntity == null) {
            throw new CustomException("Order detail not found with id: " + id);
        } else {
            orderDetailUpdate.setId(id);
            orderDetailUpdate = orderDetailRespository.save(orderDetailUpdate);
            return new ResponseObject( converter.toDTO(orderDetailUpdate));
        }
    }

    @Override
    public ResponseObject delete(Long id) throws CustomException {
        OrderDetailEntity orderDetailEntity = orderDetailRespository.findOneById(id);
        if (orderDetailEntity == null) {
            throw new CustomException("Order was not found by id: " + id);
        }
        orderDetailRespository.delete(orderDetailEntity);
        return new ResponseObject(200, "Delete order detail successfully", null);
    }

    @Override
    public ResponseObject getAllByOrderId(Long orderId) throws CustomException {
        List<OrderDetailEntity> orderDetailEntities = orderDetailRespository.findAllByOrderId(orderId);
        if (orderDetailEntities.isEmpty()) {
            throw new CustomException("Order detail not found");
        } else {
            List<OrderDetailDTO> orderDetailDTOS = orderDetailEntities.stream()
                    .map(converter::toDTO)
                    .collect(Collectors.toList());
            return new ResponseObject( orderDetailDTOS);
        }
    }

    @Override
    public ResponseObject getTotalPriceByOrderId(Long orderId) {
        int totalPrice = orderDetailRespository.getTotalPriceByOrderId(orderId);
        return new ResponseObject( totalPrice);
    }
}
