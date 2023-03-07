/** Hao Vu - haovu961@gmail.com - Jul 30, 2022 - 3:57:28 PM */
package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.ProvinceDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.ProvinceEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.repository.ProvinceRepository;
import com.fsoft.finalproject.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

  @Autowired private ProvinceRepository provinceRepository;

  @Autowired private Converter converter;

  @Override
  public ResponseObject save(ProvinceDTO provinceDTO) {

    // Check name exists
    if (checkNameExists(provinceDTO.getName())) {
      throw new CustomException("Name is already exists");
    }

    // casting provinceDTO to ProvinceEntity
    ProvinceEntity provinceEntity = converter.toEntity(provinceDTO);

    // System.out.println("district: "+ provinceEntity.getDistrictEntities());
    // save province
    //provinceEntity.setId(-1); //avoid update when id exists
    provinceRepository.save(provinceEntity);

    // return add Province success
    return new ResponseObject(converter.toDTO(provinceEntity));
  }

  @Override
  public ResponseObject update(ProvinceDTO provinceDTO) {
    // Check name Exists
    if (checkNameExists(provinceDTO.getName())) {
      throw new CustomException("Name is already exists");
    }

    boolean exists = provinceRepository.existsById(provinceDTO.getId());

    if (exists) {

      ProvinceEntity province = provinceRepository.getReferenceById(provinceDTO.getId());
      province = converter.toEntity(provinceDTO);
      provinceRepository.save(province);
      return new ResponseObject(converter.toDTO(province));
    }

    throw new CustomException("Cannot find Province with id = " + provinceDTO.getId());
  }

  @Override
  public ResponseObject delete(long id) {
    boolean exists = provinceRepository.existsById(id);
    if (exists) {
      provinceRepository.deleteById(id);
      return new ResponseObject(200, "Delete province Successfully", null);
    }
    throw new CustomException("Cannot find Province with id =" + id);
  }

  @Override
  public ResponseObject delete(String name) {
    ProvinceEntity provinceEntity = provinceRepository.getByName(name);
    if (provinceEntity != null) {
      provinceRepository.delete(provinceEntity);
      return new ResponseObject(200, "Delete province Successfully", null);
    }
    throw new CustomException("Cannot find Province with name : " + name);
  }

  @Override
  public ResponseObject deleteAll() {
    provinceRepository.deleteAll();
    return new ResponseObject(200, "Delete all province Successfully", null);
  }

  @Override
  public ResponseObject getAll() {
    // Lay Tat Ca ProvinceEntity
    List<ProvinceEntity> provinceEntities = provinceRepository.findByOrderByIdAsc();
    // Tao ProvinceDTO de tra ve Json
    List<ProvinceDTO> provinceDTOs = new ArrayList<>();

    // Cast ProvinceEntity --> ProvinceDTO
    for (ProvinceEntity provinceEntity : provinceEntities) {
      ProvinceDTO provinceDTO = converter.toDTO(provinceEntity);
      provinceDTOs.add(provinceDTO);
    }
    return new ResponseObject(provinceDTOs);
  }

  @Override
  public ResponseObject getOne(long id) {
    boolean exists = provinceRepository.existsById(id);

    if (exists) {
      ProvinceEntity province = provinceRepository.getReferenceById(id);
      return new ResponseObject(converter.toDTO(province));
    }

    throw new CustomException("Cannot find Province with id =" + id);
  }

  public boolean checkNameExists(String name) {

    ProvinceEntity province = provinceRepository.getByName(name);
      return province != null;
  }
}