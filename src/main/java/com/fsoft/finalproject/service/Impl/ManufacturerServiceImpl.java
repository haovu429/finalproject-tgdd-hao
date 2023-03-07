/** Hao Vu - haovu961@gmail.com - Aug 1, 2022 - 9:40:51 PM */
package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.ManufacturerDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.ManufacturerEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.repository.ManufacturerRepository;
import com.fsoft.finalproject.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {


  @Autowired private Converter converter;

  @Autowired private ManufacturerRepository manufacturerRepository;

  @Override
  public ResponseObject getOne(long id) {
    boolean exists = manufacturerRepository.existsById(id);

    if (exists) {
      ManufacturerEntity province = manufacturerRepository.getReferenceById(id);
      return new ResponseObject(converter.toDTO(province));
    }

    throw new CustomException("Cannot find manufacturer with id =" + id);
  }

  @Override
  public ResponseObject save(ManufacturerDTO manufacturerDTO) {
    // Check name exists
    if (checkNameExists(manufacturerDTO.getName())) {
      throw new CustomException("Name is already exists");
    }

    // casting manufacturerDTO to manufacturerEntity
    ManufacturerEntity manufacturerEntity = converter.toEntity(manufacturerDTO);
    manufacturerEntity.setId(0);
    // save province
    manufacturerRepository.save(manufacturerEntity);

    // return add Province success
    return new ResponseObject(converter.toDTO(manufacturerEntity));
  }

  @Override
  public ResponseObject update(ManufacturerDTO manufacturerDTO) {
    // Check name exists
    if (checkNameExists(manufacturerDTO.getName())) {
      throw new CustomException("Name is already exists");
    }

    boolean exists = manufacturerRepository.existsById(manufacturerDTO.getId());

    if (exists) {

      ManufacturerEntity manufacturer =
          manufacturerRepository.getReferenceById(manufacturerDTO.getId());
      manufacturer.setName(manufacturerDTO.getName());
      manufacturerRepository.save(converter.toEntity(manufacturerDTO));
      return new ResponseObject(converter.toDTO(manufacturer));
    }

    throw new CustomException("Cannot find manufacturer with id = " + manufacturerDTO.getId());
  }

  @Override
  public ResponseObject delete(long id) {
    boolean exists = manufacturerRepository.existsById(id);
    if (exists) {
      manufacturerRepository.deleteById(id);
      return new ResponseObject(200, "Delete manufacturer Successfully", null);
    }
    throw new CustomException("Cannot find manufacturer with id =" + id);
  }

  @Override
  public ResponseObject delete(String name) {
    ManufacturerEntity manufacturer = manufacturerRepository.getByName(name);
    if (manufacturer != null) {
      manufacturerRepository.delete(manufacturer);
      return new ResponseObject(200, "Delete manufacturer Successfully", null);
    }
    throw new CustomException("Cannot find manufacturer with name : " + name);
  }

  @Override
  public ResponseObject getAll() {
    // Lay Tat Ca ProvinceEntity
    List<ManufacturerEntity> manufacturerEntities = manufacturerRepository.findAll();
    // Tao ProvinceDTO de tra ve Json
    List<ManufacturerDTO> manufacturerDTOs = new ArrayList<>();

    // Cast ProvinceEntity --> ProvinceDTO
    for (ManufacturerEntity manufacturerEntity : manufacturerEntities) {
      ManufacturerDTO manufacturerDTO = converter.toDTO(manufacturerEntity);
      manufacturerDTOs.add(manufacturerDTO);
    }
    return new ResponseObject( manufacturerDTOs);
  }

  public boolean checkNameExists(String name) {

    ManufacturerEntity manufacturer = manufacturerRepository.getByName(name);
    return manufacturer != null;
  }
}
