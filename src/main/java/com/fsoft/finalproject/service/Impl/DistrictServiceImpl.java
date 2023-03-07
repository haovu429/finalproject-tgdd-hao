/** Hao Vu - haovu961@gmail.com - Jul 31, 2022 - 1:01:23 AM */
package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;

import com.fsoft.finalproject.dto.DistrictDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.DistrictEntity;
import com.fsoft.finalproject.entity.ProvinceEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.repository.DistrictRepository;
import com.fsoft.finalproject.repository.ProvinceRepository;
import com.fsoft.finalproject.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {


  @Autowired private ProvinceRepository provinceRepository;

  @Autowired private DistrictRepository districtRepository;

  @Autowired private Converter converter;

  @Override
  public ResponseObject save(DistrictDTO districtDTO) {
    // check foreign key to Province is exists
    ProvinceEntity provinceEntity;

    Long provinceId = districtDTO.getProvinceId();

    if (provinceRepository.existsById(provinceId)) {
      provinceEntity = provinceRepository.getReferenceById(provinceId);
    } else {
        throw new CustomException("Province of district is already exists");
    }

    // Check name exists
    if (checkNameExists(districtDTO.getName())) {
        throw new CustomException("Name is already exists");
    }

    // casting districtDTO to DistrictEntity
    DistrictEntity districtEntity = converter.toEntity(districtDTO);
    districtEntity.setId(0);

    districtEntity.setProvinceEntity(provinceEntity);

    // System.out.println("ward: "+ districtEntity.getWardEntities());
    // save district
    districtRepository.save(districtEntity);

    // return add District success
    return new ResponseObject(converter.toDTO(districtEntity));
  }

  @Override
  public ResponseObject update(DistrictDTO districtDTO) {
    // Check name Exists
    if (checkNameExists(districtDTO.getName())) {
      throw new CustomException("Name is already exists");
    }

    if (districtRepository.existsById(districtDTO.getId())) {
      DistrictEntity district = converter.toEntity(districtDTO);
      district.setName(districtDTO.getName());

      ProvinceEntity provinceEntity =
          provinceRepository.getReferenceById(district.getProvinceEntity().getId());
      district.setProvinceEntity(provinceEntity);
      // district.setWardEntities(null);
      districtRepository.save(district);
      return new ResponseObject(converter.toDTO(district));
    }

    throw new CustomException("Cannot find District with id = " + districtDTO.getId());
  }

  @Override
  public ResponseObject delete(long id) {
    boolean exists = districtRepository.existsById(id);
    if (exists) {
      districtRepository.deleteById(id);
      return new ResponseObject(200, "Delete district Successfully", null);
    }
    throw new CustomException("Cannot find District with id =" + id);
  }

  @Override
  public ResponseObject delete(String name) {
    DistrictEntity districtEntity = districtRepository.getByName(name);
    if (districtEntity != null) {
      districtRepository.delete(districtEntity);
      return new ResponseObject(200, "Delete district Successfully", null);
    }
    throw new CustomException("Cannot find District with name : " + name);
  }

  @Override
  public ResponseObject deleteAll() {
    districtRepository.deleteAll();
    return new ResponseObject(200, "Delete all district Successfully", null);
  }

  @Override
  public ResponseObject getAll() {
    // Lay Tat Ca DistrictEntity
    List<DistrictEntity> districtEntities = districtRepository.findAll();
    // Tao DistrictDTO de tra ve Json
    List<DistrictDTO> districtDTOs = new ArrayList<>();

    // Cast DistrictEntity --> DistrictDTO
    for (DistrictEntity districtEntity : districtEntities) {
      DistrictDTO districtDTO = converter.toDTO(districtEntity);
      districtDTOs.add(districtDTO);
    }
    return new ResponseObject(districtDTOs);
  }

  @Override
  public ResponseObject getOne(long id) {
    boolean exists = districtRepository.existsById(id);

    if (exists) {
      DistrictEntity district = districtRepository.getReferenceById(id);
      return new ResponseObject(converter.toDTO(district));
    }

    throw new CustomException("Cannot find district with id =" + id);
  }

  @Override
  public ResponseObject getByProvince(long id) {
    boolean exists = provinceRepository.existsById(id);
    if (exists) {
      List<DistrictEntity> districtEntities = districtRepository.getByProvinceId(id);

      List<DistrictDTO> districtDTOs = new ArrayList<>();

      // Cast DistrictEntity --> DistrictDTO
      for (DistrictEntity districtEntity : districtEntities) {
        DistrictDTO districtDTO = converter.toDTO(districtEntity);
        districtDTOs.add(districtDTO);
      }
      return new ResponseObject(districtDTOs);
    }
    throw new CustomException("Cannot find Province with id =" + id);
  }

  @Override
  public ResponseObject deleteByProvince(String name) {
    ProvinceEntity province = provinceRepository.getByName(name);

    if (province != null) {
      // System.out.println("Province name :" + province.getName());
      districtRepository.deleteByProvinceId(province.getId());
      return new ResponseObject(200, "Delete districts Successfully", null);
    }
    throw new CustomException("Cannot find district with province name: ");
  }

  @Override
  public ResponseObject deleteByProvince(long id) {
    boolean exists = provinceRepository.existsById(id);
    if (exists) {
      districtRepository.deleteByProvinceId(id);
      return new ResponseObject(200, "Delete districts Successfully", null);
    }
    throw new CustomException("Cannot find district with province id = ");
  }

  @Override
  public ResponseObject getByProvince(String name) {
    ProvinceEntity province = provinceRepository.getByName(name);
    if (province != null) {
      List<DistrictEntity> districtEntities = districtRepository.getByProvinceId(province.getId());

      List<DistrictDTO> districtDTOs = new ArrayList<>();

      // Cast DistrictEntity --> DistrictDTO
      for (DistrictEntity districtEntity : districtEntities) {
        DistrictDTO districtDTO = converter.toDTO(districtEntity);
        districtDTOs.add(districtDTO);
      }
		return new ResponseObject( districtDTOs);
    }
    throw new CustomException("Cannot find Province with name: " + name);
  }

  public boolean checkNameExists(String name) {
    DistrictEntity district = districtRepository.getByName(name);
      return district != null;
  }

}
