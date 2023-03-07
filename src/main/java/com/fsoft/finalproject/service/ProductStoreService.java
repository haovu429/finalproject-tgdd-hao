package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.ItemInCart;
import com.fsoft.finalproject.dto.ProductDTO;
import com.fsoft.finalproject.dto.ProductStoreDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.ProductStoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductStoreService {
  Page<ProductStoreDTO> findAll(Pageable pageable);

  Optional<ProductStoreDTO> save(ProductStoreDTO dto);

  boolean delete(long id);

  Optional<ProductStoreDTO> update(long id, ProductStoreDTO dto);

  List<ProductStoreEntity> getProductInStore(long storeId);
}
