//package com.fsoft.finalproject.converter;
//
//import com.fsoft.finalproject.dto.StoreDTO;
//import com.fsoft.finalproject.entity.StoreEntity;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.UUID;
//
//@Mapper(componentModel = "spring", imports = UUID.class)
//public interface StoreConverter {
//
//  @Mapping(source = "entity.addressEntity", target = "address")
//  StoreDTO toStoreDTO(StoreEntity entity);
//
//  @Mapping(source = "dto.address", target = "addressEntity")
//  StoreEntity toEntity(StoreDTO dto);
//}
