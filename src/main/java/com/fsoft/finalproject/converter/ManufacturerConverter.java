///** Hao Vu - haovu961@gmail.com - Aug 1, 2022 - 8:02:02 AM */
//package com.fsoft.finalproject.converter;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//import com.fsoft.finalproject.dto.ManufacturerDTO;
//import com.fsoft.finalproject.entity.ManufacturerEntity;
//
//@Component
//public class ManufacturerConverter {
//  ModelMapper mapper = new ModelMapper();
//
//  public ManufacturerDTO toDTO(ManufacturerEntity manufacturerEntity) {
//    ManufacturerDTO manufacturer = mapper.map(manufacturerEntity, ManufacturerDTO.class);
//
//    return manufacturer;
//  }
//
//  public ManufacturerEntity toEntity(ManufacturerDTO manufacturerDTO) {
//    ManufacturerEntity manufacturerEntity = mapper.map(manufacturerDTO, ManufacturerEntity.class);
//
//    return manufacturerEntity;
//  }
//}
