package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
// import com.fsoft.finalproject.converter.PhoneConverter;
import com.fsoft.finalproject.dto.CreatePhoneDTO;
import com.fsoft.finalproject.dto.PhoneDTO;
import com.fsoft.finalproject.dto.ProductDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.PhoneEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.repository.PhoneRepository;
import com.fsoft.finalproject.service.PhoneService;
import com.fsoft.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PhoneServiceImpl implements PhoneService {
  @Autowired PhoneRepository phoneRepository;

  @Autowired Converter converter;

  @Autowired ProductService productService;

  @Override
  public List<PhoneEntity> findAll() {
    return phoneRepository.findAll();
  }

  @Override
  public List<PhoneEntity> findAll(Sort sort) {
    return phoneRepository.findAll(sort);
  }

  @Override
  public Page<PhoneDTO> findAll(Pageable pageable) {

    Page<PhoneEntity> entities = phoneRepository.findAll(pageable);
    return entities.map(
        new Function<PhoneEntity, PhoneDTO>() {
          @Override
          public PhoneDTO apply(PhoneEntity phoneEntity) {
            return converter.toDTO(phoneEntity);
          }
        });
  }

  @Override
  public Optional<PhoneEntity> findById(Long aLong) {
    return phoneRepository.findById(aLong);
  }

  @Override
  public void deleteById(Long aLong) {
    phoneRepository.deleteById(aLong);
  }

  @Override
  public void delete(PhoneEntity entity) {
    phoneRepository.delete(entity);
  }

  @Override
  public boolean deletePhone(long id) {
    Optional<PhoneEntity> entity = phoneRepository.findById(id);
    if (!entity.isPresent()) {
      return false;
    }
    phoneRepository.deletePhone(id);
    return true;
  }

  @Override
  public ResponseObject savePhone(CreatePhoneDTO newPhone) {
    Optional<ProductDTO> resProduct = productService.save(newPhone.getProduct());
    if (!resProduct.isPresent()) {
      throw new CustomException("Can not save Product information");
    }
    PhoneDTO phoneDTO = newPhone.getPhone();
    phoneDTO.setId(resProduct.get().getId());
    PhoneEntity entity = converter.toEntity(phoneDTO);
    entity.setId(resProduct.get().getId());
    entity.setProductEntity(converter.toEntity(resProduct.get()));
    try {
      PhoneEntity res = phoneRepository.save(entity);
      return new ResponseObject(converter.toDTO(res));
    } catch (Exception e) {
      e.printStackTrace();
      throw new CustomException(e.getMessage());
    }
  }

  @Override
  public Optional<PhoneDTO> updatePhone(long id, PhoneDTO phoneDTO) {
    Optional<PhoneEntity> foundEntity = phoneRepository.findById(id);
    if (!foundEntity.isPresent()) {
      return Optional.ofNullable(null);
    }
    PhoneEntity newEntity = converter.toEntity(phoneDTO);
    newEntity.setId(id);
    phoneRepository.save(newEntity);

    return Optional.ofNullable(converter.toDTO(newEntity));
  }
}
