/** Hao Vu - haovu961@gmail.com - Jul 31, 2022 - 3:46:30 AM */
package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.ImageDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.ImageEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.exception.CustomerException;
import com.fsoft.finalproject.repository.ImageRepository;
import com.fsoft.finalproject.repository.ProductRepository;
import com.fsoft.finalproject.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
  // @Autowired EntityManager entityManager;

  @Autowired private ProductRepository productRepository;

  @Autowired private ImageRepository imageRepository;

  @Autowired private Converter converter;

  @Override
  public ResponseObject getOne(long id) {
    Optional<ImageEntity> image;
    try {
      image = imageRepository.findById(id);
    } catch (Exception e) {
      throw new CustomerException("Not Found image!");
    }

    if (image.isPresent()) {
      return new ResponseObject(converter.toDTO(image.get()));
    }

    throw new CustomException("Cannot find Image with id = " + id);
  }

  @Override
  public ResponseObject save(ImageDTO imageDTO) {
    // Check product id valid
    boolean exists = productRepository.existsById(imageDTO.getProductId());
    if (!exists) {
      throw new CustomException("Product Id invalid");
    }

    // casting imageDTO to imageEntity
    ImageEntity imageEntity = converter.toEntity(imageDTO);
    imageEntity.setId(0);
    // save image
    imageRepository.save(imageEntity);

    // return add Province success
    return new ResponseObject(converter.toDTO(imageEntity));
  }

  @Override
  public ResponseObject update(ImageDTO imageDTO) {

    boolean exists = productRepository.existsById(imageDTO.getProductId());
    if (!exists) {
      throw new CustomException("Product Id invalid");
    }

    boolean exist = imageRepository.existsById(imageDTO.getId());

    if (exist) {
      ImageEntity image = converter.toEntity(imageDTO);
      imageRepository.save(image);
      return new ResponseObject(converter.toDTO(image));
    }

    throw new CustomException("Cannot find Image with id = " + imageDTO.getId());
  }

  @Override
  public ResponseObject delete(long id) {
    boolean exists = imageRepository.existsById(id);
    if (exists) {
      imageRepository.deleteById(id);
      return new ResponseObject(200, "Delete image Successfully", null);
    }
    throw new CustomException("Cannot find Image with id =" + id);
  }

  @Override
  public ResponseObject deleteByProductId(Long id) {
    boolean exists = productRepository.existsById(id);
    if (exists) {
      boolean image_exists = imageRepository.existsImageEntitiesByProductEntityId(id);
      if (image_exists) {
        imageRepository.deleteByProduct(id);
        return new ResponseObject(200, "Delete image Successfully", null);
      }
      throw new CustomException("Not found image have product id = " + id);
    }
   throw new CustomException("Cannot find Image with product_id = " + id);
  }

  @Override
  public ResponseObject getAll() {
    // Lay Tat Ca ImageEntity
    List<ImageEntity> imageEntities = imageRepository.findAll();
    // Tao ImageDTO de tra ve Json
    List<ImageDTO> imageDTOs = new ArrayList<>();

    // Cast ImageEntity --> ImageDTO
    for (ImageEntity imageEntity : imageEntities) {
      ImageDTO imageDTO = converter.toDTO(imageEntity);
      imageDTOs.add(imageDTO);
    }
    return new ResponseObject(200, "Information all image in database", imageDTOs);
  }

  @Override
  public ResponseObject getImageByProduct(long productId) {

    List<ImageEntity> imageEntities = imageRepository.getByProduct(productId);
    List<ImageDTO> imageDTOs = new ArrayList<>();

    // Cast ImageEntity --> ImageDTO
    for (ImageEntity imageEntity : imageEntities) {
      ImageDTO imageDTO = converter.toDTO(imageEntity);
      imageDTOs.add(imageDTO);
    }
    if (imageDTOs.size() > 0) {
      return
              new ResponseObject(imageDTOs);
    } else {
      throw new CustomException("Not found image have product id = " + productId);
    }
  }

  @Override
  public void deleteImageByProduct(long productId) {
    // TODO Auto-generated method stub

  }
}
