///** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 11:41:18 PM */
//package com.fsoft.finalproject.converter;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.fsoft.finalproject.dto.DistrictDTO;
//import com.fsoft.finalproject.entity.DistrictEntity;
//
//@Component
//public class DistrictConverter {
//
//  @Autowired private ProvinceConverter provinceConverter;
//
//  // @Autowired
//  // private WardConverter wardConverter;
//
//  //	@Autowired
//  //	public DistrictConverter(@Lazy ProvinceConverter provinceConverter, WardConverter
//  // wardConverter) {
//  //		super();
//  //		this.provinceConverter = provinceConverter;
//  //		this.wardConverter = wardConverter;
//  //	}
//
//  ModelMapper mapper = new ModelMapper();
//
//  public DistrictDTO toDTO(DistrictEntity districtEntity) {
//    DistrictDTO district = mapper.map(districtEntity, DistrictDTO.class);
//    // cast Province
//    // district.setProvinceEntity(provinceConverter.toDTO(districtEntity.getProvinceEntity()));
//
//    // cast list District
//    //		List<WardDTO> wardDTOs = new ArrayList<>();
//    //		for (WardEntity wardEntity : districtEntity.getWards()) {
//    //			wardDTOs.add(wardConverter.toDTO(wardEntity));
//    //		}
//    //		district.setWards(wardDTOs);
//    return district;
//  }
//
//  public DistrictEntity toEntity(DistrictDTO districtDTO) {
//    DistrictEntity districtEntity = mapper.map(districtDTO, DistrictEntity.class);
//    // cast Province
//    // districtEntity.setProvinceEntity(provinceConverter.toEntity(districtDTO.getProvinceEntity()));
//    // cast list District
//    //		List<WardEntity> wardEntities = new ArrayList<>();
//    //		for (WardDTO wardDTO : districtDTO.getWards()) {
//    //			wardEntities.add(wardConverter.toEntity(wardDTO));
//    //		}
//    //		districtEntity.setWards(wardEntities);
//    return districtEntity;
//  }
//}
