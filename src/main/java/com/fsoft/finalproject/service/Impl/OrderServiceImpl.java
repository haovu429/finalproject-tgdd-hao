package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.*;
import com.fsoft.finalproject.entity.*;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.repository.*;
import com.fsoft.finalproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired private OrderRepository orderRepository;

  @Autowired private Converter converter;

  @Autowired private CustomerRepository customerRepository;

  @Autowired private OrderDetailRespository orderDetailRespository;

  @Autowired private AddressRepository addressRepository;

  @Autowired private StoreRepository storeRepository;

  @Autowired private PaymentRepository paymentRepository;

  @Autowired private ProductRepository productRepository;

  @Autowired private ProductStoreRepository productStoreRepository;

  @Override
  public ResponseObject getAll(Pageable pageable) {
    List<OrderDTO> orderDTOS =
        orderRepository.findAll(pageable).stream()
            .map(converter::toDTO)
            .collect(Collectors.toList());
    return new ResponseObject(orderDTOS);
  }

  @Override
  public ResponseObject getById(Long id) throws CustomException {
    OrderEntity orderEntity = orderRepository.findOneById(id);
    if (orderEntity == null) {
      throw new CustomException("Order not found");
    } else {
      return new ResponseObject(converter.toDTO(orderEntity));
    }
  }

  @Override
  public ResponseObject save(OrderDTO orderDTO) {
    OrderEntity orderEntity = converter.toEntity(orderDTO);
    orderEntity = orderRepository.save(orderEntity);
    return new ResponseObject(converter.toDTO(orderEntity));
  }

  @Override
  public ResponseObject update(Long id, OrderDTO orderDTO) throws CustomException {
    OrderEntity orderUpdate = converter.toEntity(orderDTO);

    OrderEntity orderEntity = orderRepository.findOneById(id);
    if (orderEntity == null) {
      throw new CustomException("Order not found");
    } else {
      orderUpdate.setId(id);
      orderUpdate = orderRepository.save(orderUpdate);
      return new ResponseObject(converter.toDTO(orderUpdate));
    }
  }

  @Override
  public ResponseObject delete(Long id) throws CustomException {
    OrderEntity orderEntity = orderRepository.findOneById(id);
    if (orderEntity == null) {
      throw new CustomException("Order not found");
    }
    orderRepository.delete(orderEntity);
    return new ResponseObject(200, "Delete order successfully", null);
  }

  @Override
  public ResponseObject saveCartToOder(Cart cart) {
    OrderEntity orderEntity = new OrderEntity();
    //    check if customer exist
    CustomerEntity customerEntity =
        customerRepository.findOneByPhone(cart.getCustomer().getPhone());
    if (customerEntity == null) {
      customerEntity = customerRepository.save(converter.toEntity(cart.getCustomer()));
      orderEntity.setCustomerEntity(customerEntity);
    } else {
      orderEntity.setCustomerEntity(customerEntity);
    }
    //    check if address exist
    if (cart.getAddressResponseDTO().getId() != 0) {
      Optional<StoreEntity> storeEntity =
          storeRepository.findStoreEntiyByAddressId(cart.getAddressResponseDTO().getId());
      if (storeEntity.isPresent()) {
        orderEntity.setStoreEntity(storeEntity.get());
      } else {
        throw new CustomException("Address not found");
      }
    } else {
      //      Create new address for customer
      CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
      customerAddressDTO.setCustomerId(customerEntity.getId());
      customerAddressDTO.setWardId(cart.getAddressResponseDTO().getWard().getId());
      customerAddressDTO.setNumHouse(cart.getAddressResponseDTO().getNumHouse());

      AddressEntity addressEntity = addressRepository.save(converter.toEntity(customerAddressDTO));
    }
    //    status
    orderEntity.setStatus("Pending");
    orderEntity.setTotal(cart.getTotal());
    //    ordercode
    orderEntity.setOrderCode("OD" + customerEntity.getId() + System.currentTimeMillis());
    //    date
    orderEntity.setDate(new Date());
    //    check if payment exist
    if (cart.getPaymentId() != 0) {
      Optional<PaymentEntity> paymentEntity =
          Optional.ofNullable(paymentRepository.findOneById(cart.getPaymentId()));
      if (paymentEntity.isPresent()) {
        orderEntity.setPaymentEntity(paymentEntity.get());
      } else {
        throw new CustomException("Payment not found");
      }
    } else {
      throw new CustomException("Payment not valid");
    }
    //    note
    orderEntity.setNote(cart.getNote());

    //    save order
    orderEntity = orderRepository.save(orderEntity);

    //    save order detail
    for (ItemInCart itemInCart : cart.getItems()) {
      OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
      orderDetailEntity.setOrderEntity(orderEntity);
      ProductEntity productEntity = productRepository.findOneById(itemInCart.getProductId());
      orderDetailEntity.setProductEntity(productEntity);
      orderDetailEntity.setQuantity(itemInCart.getQuantity());
      orderDetailEntity.setPrice(productEntity.getPrice());

      orderDetailRespository.save(orderDetailEntity);

      //    Update quantity product in product Store
      ProductStoreEntity productStoreEntity =
          productStoreRepository.getByProductIdAndStoreId(
              productEntity.getId(), orderEntity.getStoreEntity().getId());
      productStoreEntity.setQuantity(productStoreEntity.getQuantity() - itemInCart.getQuantity());
      productStoreRepository.save(productStoreEntity);
    }

    return new ResponseObject(200, "Save cart to order successfully", converter.toDTO(orderEntity));
  }

  @Override
  public ResponseObject getByCustomerPhone(String phone) throws CustomException {
    List<OrderEntity> orderEntities = orderRepository.findAllByCustomerPhone(phone);
    if (orderEntities.isEmpty()) {
      throw new RuntimeException("Order not found");
    }
    List<OrderDTO> orderDTOS = orderEntities.stream()
            .map(converter::toDTO)
            .collect(Collectors.toList());

    return new ResponseObject(orderDTOS);
  }
}
