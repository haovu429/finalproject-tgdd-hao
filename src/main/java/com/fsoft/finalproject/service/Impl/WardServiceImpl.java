/** Hao Vu - haovu961@gmail.com - Jul 31, 2022 - 1:01:46 AM */
package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.WardDTO;
import com.fsoft.finalproject.entity.DistrictEntity;
import com.fsoft.finalproject.entity.WardEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.repository.DistrictRepository;
import com.fsoft.finalproject.repository.WardRepository;
import com.fsoft.finalproject.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WardServiceImpl implements WardService {
  @Autowired private Converter converter;

  @Autowired private DistrictRepository districtRepository;

  @Autowired private WardRepository wardRepository;

  @Override
  public ResponseObject getOne(long id) {
    boolean exists = wardRepository.existsById(id);

    if (exists) {
      WardEntity ward = wardRepository.getReferenceById(id);
      return new ResponseObject(converter.toDTO(ward));
    }

    throw new CustomException("Cannot find Ward with id =" + id);
  }

  @Override
  public ResponseObject save(WardDTO wardDTO) {
    // Check name exists
    if (checkNameExists(wardDTO.getName())) {
      throw new CustomException("Name is already exists");
    }

    // casting wardDTO to WardEntity
    WardEntity wardEntity = converter.toEntity(wardDTO);

    wardEntity.setId(0);
    // save ward
    wardRepository.save(wardEntity);

    // return add ward success
    return new ResponseObject(converter.toDTO(wardEntity));
  }

  @Override
  public ResponseObject update(WardDTO wardDTO) {
    // Check name Exists
    if (checkNameExists(wardDTO.getName())) {
      throw new CustomException("Name is already exists");
    }

    if (wardRepository.existsById(wardDTO.getId())) {

      WardEntity ward = converter.toEntity(wardDTO);
      ward.setName(wardDTO.getName());

      Long districtId = ward.getDistrictEntity().getId();
      DistrictEntity districtEntity = districtRepository.getReferenceById(districtId);
      ward.setDistrictEntity(districtEntity);

      wardRepository.save(ward);
      return new ResponseObject(converter.toDTO(ward));
    }

    throw new CustomException("Cannot find Ward with id = " + wardDTO.getId());
  }

  @Override
  public ResponseObject delete(long id) {
    boolean exists = wardRepository.existsById(id);
    if (exists) {
      wardRepository.deleteById(id);
      return new ResponseObject(200, "Delete ward Successfully", null);
    }
    throw new CustomException("Cannot find Ward with id =" + id);
  }

  @Override
  public ResponseObject delete(String name) {
    WardEntity wardEntity = wardRepository.getByName(name);
    if (wardEntity != null) {
      wardRepository.delete(wardEntity);
      return new ResponseObject(200, "Delete ward Successfully", null);
    }
    throw new CustomException("Cannot find Ward with name : " + name);
  }

  @Override
  public ResponseObject deleteAll() {
    wardRepository.deleteAll();
    return new ResponseObject(200, "Delete all ward Successfully", null);
  }

  @Override
  public ResponseObject getAll() {
    // Lay Tat Ca WardEntity
    List<WardEntity> wardEntities = wardRepository.findAll();
    // Tao WardDTO de tra ve Json
    List<WardDTO> wardDTOs = new ArrayList<>();

    // Cast WardEntity --> WardDTO
    for (WardEntity wardEntity : wardEntities) {
      WardDTO wardDTO = converter.toDTO(wardEntity);
      wardDTOs.add(wardDTO);
    }
    return new ResponseObject(wardDTOs);
  }

  @Override
  public ResponseObject getByDistrict(long id) {
    boolean exists = districtRepository.existsById(id);
    if (exists) {
      List<WardEntity> wardEntities = wardRepository.getByDistrictId(id);

      List<WardDTO> wardDTOs = new ArrayList<>();

      // Cast DistrictEntity --> DistrictDTO
      for (WardEntity wardEntity : wardEntities) {
        WardDTO wardDTO = converter.toDTO(wardEntity);
        wardDTOs.add(wardDTO);
      }
      return new ResponseObject(wardDTOs);
    }
    throw new CustomException("Cannot find district with id =" + id);
  }

  @Override
  public ResponseObject getByDistrict(String name) {
    DistrictEntity district = districtRepository.getByName(name);
    if (district != null) {
      List<WardEntity> wardEntities = wardRepository.getByDistrictId(district.getId());

      List<WardDTO> wardDTOs = new ArrayList<>();

      // Cast DistrictEntity --> DistrictDTO
      for (WardEntity wardEntity : wardEntities) {
        WardDTO wardDTO = converter.toDTO(wardEntity);
        wardDTOs.add(wardDTO);
      }
      return new ResponseObject(wardDTOs);
    }
    throw new CustomException("Cannot find district with name: " + name);
  }

  @Override
  public ResponseObject deleteByDistrict(long id) {
    boolean exists = districtRepository.existsById(id);
    if (exists) {
      wardRepository.deleteByDistrictId(id);
      return new ResponseObject(200, "Delete wards Successfully", null);
    }
    throw new CustomException("Cannot find ward with district id = " + id);
  }

  @Override
  public ResponseObject deleteByDistrict(String name) {
    DistrictEntity province = districtRepository.getByName(name);

    if (province != null) {
      System.out.println(province.getId());
      wardRepository.deleteByDistrictId(province.getId());
      return new ResponseObject(200, "Delete wards Successfully", null);
    }
    throw new CustomException("Cannot find ward with district name: " + name);
  }

  public boolean checkNameExists(String name) {
    WardEntity ward = wardRepository.getByName(name);
    return ward != null;
  }
}
