package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.CreatePhoneDTO;
import com.fsoft.finalproject.dto.PhoneDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.PhoneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    List<PhoneEntity> findAll();

    List<PhoneEntity> findAll(Sort sort);

    Page<PhoneDTO> findAll(Pageable pageable);

    Optional<PhoneEntity> findById(Long aLong);

    void deleteById(Long aLong);

    void delete(PhoneEntity entity);

    boolean deletePhone(long id);

    ResponseObject savePhone(CreatePhoneDTO newPhone);

    Optional<PhoneDTO> updatePhone(long id, PhoneDTO phoneDTO);
}
