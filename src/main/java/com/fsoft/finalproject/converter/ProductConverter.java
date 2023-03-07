///** Hao Vu - haovu961@gmail.com - Jul 31, 2022 - 6:31:47 AM */
//package com.fsoft.finalproject.converter;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.fsoft.finalproject.dto.ManufacturerDTO;
//import com.fsoft.finalproject.dto.ProductDTO;
//import com.fsoft.finalproject.entity.ProductEntity;
//
//@Component
//public class ProductConverter {
//  ModelMapper mapper = new ModelMapper();
//
//  @Autowired private ManufacturerConverter manufacturerConverter;
//
//  public ProductDTO toDTO(ProductEntity productEntity) {
//    ProductDTO product = mapper.map(productEntity, ProductDTO.class);
//    if (productEntity.getManufacturerEntity() != null) {
//      product.setManufacturer(manufacturerConverter.toDTO(productEntity.getManufacturerEntity()));
//    }
//
//    return product;
//  }
//
//  public ProductEntity toEntity(ProductDTO productDTO) {
//    ProductEntity productEntity = mapper.map(productDTO, ProductEntity.class);
//    // long a = productDTO.getManufacturer();
//
//    if (productDTO.getManufacturer() != null) {
//      productEntity.setManufacturerEntity(
//          manufacturerConverter.toEntity(productDTO.getManufacturer()));
//    }
//    return productEntity;
//  }
//}
