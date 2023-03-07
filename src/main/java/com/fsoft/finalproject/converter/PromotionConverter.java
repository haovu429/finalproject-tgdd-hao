//package com.fsoft.finalproject.converter;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.fsoft.finalproject.dto.ProductDTO;
//import com.fsoft.finalproject.dto.PromotionDTO;
//import com.fsoft.finalproject.entity.ProductEntity;
//import com.fsoft.finalproject.entity.PromotionEntity;
//import com.fsoft.finalproject.repository.ProductRepository;
//
//@Component
//public class PromotionConverter {
//  ModelMapper mapper = new ModelMapper();
//
//  @Autowired private ProductRepository productRepository;
//
//  public PromotionEntity toEntity(PromotionDTO promotion) {
//    PromotionEntity promotionEntity = mapper.map(promotion, PromotionEntity.class);
//    List<ProductDTO> productDTOs = promotion.getProducts();
//    for (ProductDTO item : productDTOs) {
//      ProductEntity proEntity = productRepository.getProductByCode(item.getProductCode());
//      if (proEntity != null) {
//        if (!promotionEntity.getProductEntities().contains(proEntity)) {
//          promotionEntity.getProductEntities().add(proEntity);
//          proEntity.getPromotionEntities().add(promotionEntity);
//        }
//      } else {
//        System.out.println("===NULL====");
//      }
//    }
//    return promotionEntity;
//  }
//
//  public PromotionDTO toDTO(PromotionEntity promotionEntity) {
//    PromotionDTO promotionDTO = mapper.map(promotionEntity, PromotionDTO.class);
//    List<ProductDTO> products =
//        mapper.map(
//            promotionEntity.getProductEntities(), new TypeToken<List<ProductDTO>>() {}.getType());
//    promotionDTO.setProducts(products);
//
//    return promotionDTO;
//  }
//}
