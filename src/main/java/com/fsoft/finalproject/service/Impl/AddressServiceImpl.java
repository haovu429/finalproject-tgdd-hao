package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.*;

import com.fsoft.finalproject.entity.AddressEntity;
import com.fsoft.finalproject.entity.CustomerEntity;
import com.fsoft.finalproject.entity.OrderEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.repository.AddressRepository;
import com.fsoft.finalproject.repository.CustomerRepository;
import com.fsoft.finalproject.service.AddressService;
// import com.fsoft.finalproject.utils.InvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  Converter converter;

  /**
   * find address by id (ok)
   *
   * @param aLong
   * @return
   */
  @Override
  public ResponseObject findById(Long aLong) throws CustomException {
    Optional<AddressEntity> dto = addressRepository.findById(aLong);
    if (!dto.isPresent()) {
      throw new CustomException("Can not found Address with id " + aLong);
    }
    return new ResponseObject(converter.toDTO(dto.get()));
  }

  /**
   * delete address by id (ok)
   *
   * @param id
   * @return
   */
  @Override
  public ResponseObject deleteById(Long id) {
    Optional<AddressEntity> foundEntity = addressRepository.findById(id);
    if (!foundEntity.isPresent()) {
      throw new RuntimeException("Can not found Address with id " + id);
    }
    addressRepository.delete(foundEntity.get());
    return new ResponseObject(200, " successful delete address with id " + id, null);
  }

  /**
   * find and pagination (ok)
   *
   * @param pageable
   * @return
   */
  @Override
  public ResponseObject findAll(Pageable pageable) {

    Page<AddressEntity> entities = addressRepository.findAll(pageable);
    Page<AddressDTO> dtos =
            entities.map(
                    new Function<AddressEntity, AddressDTO>() {
                      @Override
                      public AddressDTO apply(AddressEntity entity) {
                        return converter.toDTO(entity);
                      }
                    });
    return new ResponseObject(dtos);
  }

  @Override
  public ResponseObject findByCustomerId(Long customerId) {
    Optional<CustomerEntity> customer = customerRepository.findById(customerId);
    if (!customer.isPresent()) {
      throw new CustomException("Can not found Customer with id " + customerId);
    }
    List<AddressEntity> entities = customer.get().getAddressEntities();
    List<AddressResponseDTO> dtos = entities.stream().map(converter::toAddressResponseDTO).collect(Collectors.toList());
    return new ResponseObject(dtos);
  }

  /**
   * in version, save address but don't check provinceID, districtID and wardID
   *
   * @param dto
   * @return
   */
  @Override
  public ResponseObject saveCustomerAddress(CustomerAddressDTO dto) throws CustomException {

    AddressEntity entity = converter.toEntity(dto);
    AddressEntity savedEntity = addressRepository.save(entity);
    return new ResponseObject(converter.toDTO(savedEntity));
  }

  @Override
  public ResponseObject saveStoreAddress(AddressDTO dto) throws CustomException {

    // check info to make sure that no duplicate store's address
    List<Long> addressExisted =
            addressRepository.checkStoreAddressExist(dto.getWardId(), dto.getNumHouse());
    if (!addressExisted.isEmpty()) {
      throw new CustomException("Address of store can not duplicate");
    }

    AddressEntity entity = converter.toEntity(dto);
    try {
      AddressEntity res = addressRepository.save(entity);
      return new ResponseObject(converter.toDTO(res));
    } catch (Exception e) {
      throw new CustomException("province or district or ward not valid");
    }
  }

  @Override
  public ResponseObject updateStoreAddress(long id, AddressDTO dto) throws CustomException {
    Optional<AddressEntity> found = addressRepository.findById(id);
    if (!found.isPresent()) throw new CustomException("Address id not valid");

    List<Long> addressExisted =
            addressRepository.checkStoreAddressExist(dto.getWardId(), dto.getNumHouse());
    if (!addressExisted.isEmpty()) {
      throw new CustomException("Address of store can not duplicate");
    }

    AddressEntity updateEntity = converter.toEntity(dto);
    updateEntity.setId(found.get().getId());
    AddressEntity res = addressRepository.save(updateEntity);
    return new ResponseObject(converter.toDTO(res));
  }

  @Override
  public ResponseObject updateCustomerAddress(long customerId, AddressDTO addressDTO)
          throws CustomException {
    Optional<CustomerEntity> customer = customerRepository.findById(customerId);

    // get customer
    if (customer.isEmpty()) {
      throw new CustomException("Not found customer have id = " + customerId);
    }

    boolean checkExistsAddressOfCustomer = false;

    // get id of Address which customer have
    for (AddressEntity addressEntity : customer.get().getAddressEntities()) {
      if (addressEntity.getId() == addressDTO.getId()) {
        checkExistsAddressOfCustomer = true;
      }
    }

    Optional<AddressEntity> address;
    if (!checkExistsAddressOfCustomer) {
      throw new CustomException("Not found address of customer have id = " + addressDTO.getId());
    }

    AddressEntity updateEntity = converter.toEntity(addressDTO);
    AddressEntity res = addressRepository.save(updateEntity);
    return new ResponseObject(converter.toDTO(res));
  }

  @Override
  public ResponseObject getAllByCustomerPhone(String phone) {
    List<AddressEntity> addressEntities = addressRepository.findAllByCustomerPhone(phone);
    if (addressEntities.isEmpty()) {
      throw new RuntimeException("Address not found");
    }
    List<AddressDTO> addressDTOS = addressEntities.stream()
            .map(converter::toDTO)
            .collect(Collectors.toList());

    return new ResponseObject(addressDTOS);
  }
}
