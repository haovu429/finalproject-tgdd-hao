package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.ItemInCart;
import com.fsoft.finalproject.dto.ProductDTO;
import com.fsoft.finalproject.dto.ProductStoreDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.ProductEntity;
import com.fsoft.finalproject.entity.ProductStoreEntity;
import com.fsoft.finalproject.repository.ProductStoreRepository;
import com.fsoft.finalproject.service.ProductStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductStoreServiceImpl implements ProductStoreService {
  @Autowired
  ProductStoreRepository productStoreRepository;

  @Autowired
  Converter converter;

  @Autowired
  StoreServiceImpl storeService;

  @Override
  public Page<ProductStoreDTO> findAll(Pageable pageable) {
    Page<ProductStoreEntity> entities = productStoreRepository.findAll(pageable);
    return entities.map(
            new Function<ProductStoreEntity, ProductStoreDTO>() {
              @Override
              public ProductStoreDTO apply(ProductStoreEntity productStoreEntity) {
                return converter.toDTO(productStoreEntity);
              }
            });
  }

  @Override
  public Optional<ProductStoreDTO> save(ProductStoreDTO dto) {
    // check product id later
    if (!storeService.findById(dto.getStoreId()).isPresent() || dto.getQuantity() <= 0) {
      return Optional.ofNullable(null);
    }
    ProductStoreEntity entity = converter.toEntity(dto);
    ProductStoreEntity res = productStoreRepository.save(entity);
    return Optional.of(converter.toDTO(res));
  }

  @Override
  public boolean delete(long id) {
    if (!productStoreRepository.findById(id).isPresent()) {
      return false;
    }
    try {
      productStoreRepository.deleteById(id);
    } catch (Exception e) {
      throw e;
    }
    return true;
  }

  @Override
  public Optional<ProductStoreDTO> update(long id, ProductStoreDTO dto) {
    boolean hasNull = Stream.of(dto).anyMatch(Objects::isNull);
    if (dto.getQuantity() < 0) {
      return Optional.ofNullable(null);
    }
    ProductStoreEntity newEntity = converter.toEntity(dto);
    ProductStoreEntity foundEntity = productStoreRepository.findById(id).get();
    if (foundEntity != null) {
      newEntity.setId(id);
      productStoreRepository.save(newEntity);
      return Optional.of(converter.toDTO(newEntity));
    }
    return Optional.ofNullable(null);
  }

  @Override
  public List<ProductStoreEntity> getProductInStore(long storeId) {
    List<ProductStoreEntity> productsInStore = productStoreRepository.getProductInStore(storeId);
    return productsInStore;
  }
}
