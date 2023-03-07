package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.CreateLapTopDTO;
import com.fsoft.finalproject.dto.LaptopDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import org.springframework.data.domain.Pageable;

public interface LaptopService {

    ResponseObject findAll(Pageable pageable);

    ResponseObject deleteById(long id) throws CustomException;

    ResponseObject updateLaptop(LaptopDTO dto, long id) throws CustomException;

    ResponseObject savelaptop(CreateLapTopDTO newLaptop) throws CustomException;
}
