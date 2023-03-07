package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.ItemInCart;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.StoreDTO;
import com.fsoft.finalproject.entity.StoreEntity;
import com.fsoft.finalproject.exception.InvalidException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StoreService {

  Page<StoreDTO> findAll(Pageable pageable);

  Optional<?> save(StoreDTO dto) throws InvalidException;

  Optional<StoreDTO> findById(Long aLong);

  Optional<StoreDTO> update(long id, StoreDTO dto) throws InvalidException;

  boolean deleteById(long id) throws InvalidException;

  void delete(StoreEntity entity);

  ResponseObject findStoreByDistrict(long districtId);

  ResponseObject findStoreByAddressAndQuantityProductInCart(long provinceID, long districtID, List<ItemInCart> listItems);
}
