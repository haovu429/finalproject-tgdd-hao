//package com.fsoft.finalproject.converter;
//
//import com.fsoft.finalproject.dto.ProductStoreDTO;
//import com.fsoft.finalproject.entity.ProductEntity;
//import com.fsoft.finalproject.entity.ProductStoreEntity;
//import com.fsoft.finalproject.entity.StoreEntity;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import org.mapstruct.Named;
//
//import java.util.UUID;
//
//@Mapper(componentModel = "spring", imports = UUID.class)
//public interface ProductStoreConverter {
//
//  @Mappings({
//    @Mapping(source = "entity.productEntity.id", target = "productCode"),
//    @Mapping(source = "entity.storeEntity.id", target = "storeId"),
//  })
//  ProductStoreDTO toProductStoreDTO(ProductStoreEntity entity);
//
//  @Mappings({
//    @Mapping(source = "dto", target = "productEntity", qualifiedByName = "productEntity"),
//    @Mapping(source = "dto", target = "storeEntity", qualifiedByName = "storeEntity"),
//  })
//  ProductStoreEntity toEntity(ProductStoreDTO dto);
//
//  @Named("productEntity")
//  @Mapping(source = "productCode", target = "id")
//  ProductEntity toProduct(ProductStoreDTO dto);
//
//  @Named("storeEntity")
//  @Mapping(source = "storeId", target = "id")
//  StoreEntity toStore(ProductStoreDTO dto);
//}
