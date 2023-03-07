///** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 11:41:50 PM */
//package com.fsoft.finalproject.converter;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.fsoft.finalproject.dto.WardDTO;
//import com.fsoft.finalproject.entity.WardEntity;
//
//@Component
//public class WardConverter {
//  ModelMapper mapper = new ModelMapper();
//
//  @Autowired private DistrictConverter districtConverter;
//
//  //	@Autowired
//  //	public WardConverter(@Lazy DistrictConverter districtConverter) {
//  //		super();
//  //		this.districtConverter = districtConverter;
//  //	}
//
//  public WardDTO toDTO(WardEntity wardEntity) {
//    WardDTO ward = mapper.map(wardEntity, WardDTO.class);
//    // ward.setDistrictEntity(districtConverter.toDTO(wardEntity.getDistrictEntity()));
//    return ward;
//  }
//
//  public WardEntity toEntity(WardDTO wardDTO) {
//    WardEntity wardEntity = mapper.map(wardDTO, WardEntity.class);
//    // wardEntity.setDistrictEntity(districtConverter.toEntity(wardDTO.getDistrictEntity()));
//    return wardEntity;
//  }
//
//  public DistrictConverter getDistrictConverter() {
//    return districtConverter;
//  }
//}
