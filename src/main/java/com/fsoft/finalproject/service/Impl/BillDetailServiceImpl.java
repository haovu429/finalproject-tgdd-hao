package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.BillDetailDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.BillDetailEntity;
import com.fsoft.finalproject.entity.BillEntity;
import com.fsoft.finalproject.exception.CustomException;
import com.fsoft.finalproject.repository.BillDetailRepository;
import com.fsoft.finalproject.repository.BillRepository;
import com.fsoft.finalproject.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillDetailServiceImpl implements BillDetailService {

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private Converter converter;

    @Autowired
    private BillRepository billRepository;

    @Override
    public ResponseObject getBillDetailByBillId(Long billId) throws CustomException {
        BillEntity bill = billRepository.findOneById(billId);
        if(bill == null)
            throw new CustomException("can not find bill with id: " + billId);

        List<BillDetailDTO> billDetailDTOs = new ArrayList<>();
        billDetailRepository.findAllByBillId(billId).forEach(billDetail -> billDetailDTOs.add(converter.toDTO(billDetail)));
        return new ResponseObject(billDetailDTOs);
    }

    @Override
    public ResponseObject save(BillDetailDTO billDetailDTO) throws CustomException {
        BillEntity bill = billRepository.findOneById(billDetailDTO.getBillId());
        if(bill == null)
            throw new CustomException("can not fin bill with id: " + billDetailDTO.getBillId());

        //Cast DTO --> Entity
        BillDetailEntity billDetailEntity = converter.toEntity(billDetailDTO);

        // Save BillDetail
        billDetailEntity = billDetailRepository.save(billDetailEntity);
        return new ResponseObject(converter.toDTO(billDetailEntity));
    }

    @Override
    public ResponseObject update(Long id, BillDetailDTO billDetailDTO) throws CustomException {
        //Cast DTO --> Entity
        BillDetailEntity billDetailEntityUpdate = converter.toEntity(billDetailDTO);

        // Find BillDetail by id
        BillDetailEntity billDetailEntity = billDetailRepository.findOneById(id);
        if (billDetailEntity == null) {
             throw new CustomException("can not find bill with id: " + id);
        } else {
            billDetailEntityUpdate.setId(billDetailEntity.getId());
            billDetailEntity = billDetailRepository.save(billDetailEntityUpdate);
            return new ResponseObject(converter.toDTO(billDetailEntity));
        }
    }

    @Override
    public ResponseObject delete(Long id) throws CustomException {
        // Find BillDetail by id
        BillDetailEntity billDetailEntity = billDetailRepository.findOneById(id);
        if (billDetailEntity == null) {
            throw new CustomException("can not find bill with id: " + id);
        } else {
            billDetailRepository.delete(billDetailEntity);
            return new  ResponseObject(200, "Delete bill detail Success", null);
        }
    }

    @Override
    public ResponseObject getAll(Pageable pageable) {
        List<BillDetailDTO> billDetailDTOs = billDetailRepository.findAll(pageable)
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
        return new ResponseObject( billDetailDTOs);
    }

    @Override
    public ResponseObject getById(Long id) throws CustomException {
        BillDetailEntity billDetailEntity = billDetailRepository.findOneById(id);
        if (billDetailEntity == null) {
            throw new CustomException("can not found bill with id: " + id);
        } else {
            return new ResponseObject(200, "Get bill detail Success", converter.toDTO(billDetailEntity));
        }
    }
}
