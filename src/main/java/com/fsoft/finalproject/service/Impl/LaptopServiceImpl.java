package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
// import com.fsoft.finalproject.converter.LaptopConverter;
import com.fsoft.finalproject.dto.CreateLapTopDTO;
import com.fsoft.finalproject.dto.LaptopDTO;
import com.fsoft.finalproject.dto.ProductDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.LaptopEntity;
import com.fsoft.finalproject.entity.ProductEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.repository.LaptopRepository;
import com.fsoft.finalproject.repository.ProductRepository;
import com.fsoft.finalproject.service.LaptopService;
import com.fsoft.finalproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.function.Function;

@Service
public class LaptopServiceImpl implements LaptopService {

  @Autowired
  LaptopRepository laptopRepository;

  @Autowired
  Converter converter;

  @Autowired ProductService productService;

  @Autowired
  ProductRepository productRepository;

  @PersistenceContext public EntityManager em;

  @Override
  public ResponseObject findAll(Pageable pageable) {
    Page<LaptopEntity> entities = laptopRepository.findAll(pageable);
    Page<LaptopDTO> dtos =
        entities.map(
            new Function<LaptopEntity, LaptopDTO>() {
              @Override
              public LaptopDTO apply(LaptopEntity entity) {
                return converter.toDTO(entity);
              }
            });
    return new ResponseObject(dtos);
  }

  @Override
  public ResponseObject deleteById(long id) {
    Optional<LaptopEntity> foundEntity = laptopRepository.findById(id);
    if (!foundEntity.isPresent()) {
      throw new CustomException("Can not find with id: " + id);
    }
    laptopRepository.deleteById(id);
    return new ResponseObject(200, "Delete Laptop successful", null);
  }

  @Override
  public ResponseObject updateLaptop(LaptopDTO dto, long id) throws CustomException {
    Optional<LaptopEntity> foundEntity = laptopRepository.findById(id);
    if (!foundEntity.isPresent()) {
      throw new CustomException("Can not find laptop with id: " + id);
    }
    LaptopEntity newEntity = converter.toEntity(dto);
    newEntity.setId(id);
    return new ResponseObject(converter.toDTO(laptopRepository.save(newEntity)));
  }

  //  public ResponseObject saveOnlyLaptop(LaptopDTO laptopDTO){
  //    LaptopEntity entity = converter.toEntity(laptopDTO);
  //    LaptopEntity res = laptopRepository.save(entity);
  //
  //    return new ResponseObject(res);
  //  }

  //@Transactional
  @Override
  public ResponseObject savelaptop(CreateLapTopDTO newLaptop) throws CustomException {
    //https://www.baeldung.com/hibernate-identifiers
    Optional<ProductDTO> resProduct = productService.save(newLaptop.getProduct());
    if (!resProduct.isPresent()) {
      throw new CustomException("Can not save Product");
    }
    ProductEntity productEntity = productRepository.findOneById(resProduct.get().getId());
    LaptopDTO laptopDTO = newLaptop.getLaptop();
    // laptopDTO.setId(id); id form ProductEntity is added
    LaptopEntity laptop = converter.toEntity(laptopDTO);
    laptop.setProductEntity(productEntity);

    laptopRepository.save(laptop);
    return new ResponseObject(converter.toDTO(laptop));
  }
}
