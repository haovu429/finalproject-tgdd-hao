///** Hao Vu - haovu961@gmail.com - Jul 31, 2022 - 12:36:41 AM */
//package com.fsoft.finalproject.converter;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.fsoft.finalproject.dto.ImageDTO;
//import com.fsoft.finalproject.entity.ImageEntity;
//
//@Component
//public class ImageConverter {
//
//  @Autowired private ProductConverter productConverter;
//
//  ModelMapper mapper = new ModelMapper();
//
//  public ImageDTO toDTO(ImageEntity imageEntity) {
//    ImageDTO image = mapper.map(imageEntity, ImageDTO.class);
//
//    return image;
//  }
//
//  public ImageEntity toEntity(ImageDTO imageDTO) {
//    ImageEntity imageEntity = mapper.map(imageDTO, ImageEntity.class);
//    imageEntity.setProductEntity(productConverter.toEntity(imageDTO.getProduct()));
//
//    return imageEntity;
//  }
//}
