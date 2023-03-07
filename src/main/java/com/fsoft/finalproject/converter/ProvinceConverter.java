/// ** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 4:29:28 PM */
// package com.fsoft.finalproject.converter;
//
// import org.modelmapper.ModelMapper;
// import org.springframework.stereotype.Component;
//
// import com.fsoft.finalproject.dto.ProvinceDTO;
// import com.fsoft.finalproject.entity.ProvinceEntity;
//
// @Component
// public class ProvinceConverter {
//
//  // @Autowired
//  // private DistrictConverter districtConverter;
//
//  ModelMapper mapper = new ModelMapper();
//
//  public ProvinceDTO toDTO(ProvinceEntity provinceEntity) {
//    ProvinceDTO province = mapper.map(provinceEntity, ProvinceDTO.class);
//
//    // cast list DistriDistrictDTO> districtDTOs = new ArrayList<>();
////    //		for (DistrictEntct
//    /trictEntity districtEntity : provinceEntity.getDistrictEntities()) {
//    //			districtDTOs.add(districtConverter.toDTO(districtEntity));
//    //		}
//    //		province.setDistricts(districtDTOs);
//    return province;
//  }
//
//  public ProvinceEntity toEntity(ProvinceDTO provinceDTO) {
//    ProvinceEntity provinceEntity = mapper.map(provinceDTO, ProvinceEntity.class);
//
//    //		List<DistrictEntity> districtEntities = new ArrayList<>();
//    //		for (DistrictDTO districtDTO : provinceDTO.getDistricts()) {
//    //			districtEntities.add(districtConverter.toEntity(districtDTO));
//    //		}
//    //		provinceEntity.setDistrictEntities(districtEntities);
//    return provinceEntity;
//  }
// }
