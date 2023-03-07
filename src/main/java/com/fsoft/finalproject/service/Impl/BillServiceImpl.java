package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.BillDTO;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.BillEntity;
import com.fsoft.finalproject.repository.BillRepository;
import com.fsoft.finalproject.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private Converter converter;

    @Override
    public ResponseObject save(BillDTO billDTO) {
        BillEntity billEntity = converter.toEntity(billDTO);
        billEntity = billRepository.save(billEntity);
        return new ResponseObject(converter.toDTO(billEntity));
    }

    @Override
    public ResponseObject update(long id, BillDTO billDTO) {
        BillEntity billEntityUpdate = converter.toEntity(billDTO);

        BillEntity billEntity = billRepository.findOneById(id);
        if (billEntity == null) {
            throw new RuntimeException("Bill not found");
        } else {
            billEntityUpdate.setId(billEntity.getId());
            billEntity = billRepository.save(billEntityUpdate);
            return new ResponseObject(converter.toDTO(billEntity));
        }
    }

    @Override
    public ResponseObject delete(long id) {
        BillEntity billEntity = billRepository.findOneById(id);
        if (billEntity == null) {
            throw new RuntimeException("Bill not found");
        }
        billRepository.delete(billEntity);
        return new ResponseObject(200, "Delete success", "");
    }

    @Override
    public ResponseObject getAll(Pageable pageable) {
        List<BillDTO> billDTOS = billRepository.findAll(pageable).stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());

        return new ResponseObject(billDTOS);
    }

    @Override
    public ResponseObject getById(long id) {
        BillEntity billEntity = billRepository.findOneById(id);
        if (billEntity == null) {
            throw new RuntimeException("Bill not found");
        }
        return new ResponseObject(converter.toDTO(billEntity));
    }

    @Override
    public ResponseObject getAllByCustomerPhone(String phone) {
        List<BillEntity> billEntities = billRepository.findAllByCustomerPhone(phone);
        if (billEntities.isEmpty()) {
            throw new RuntimeException("Bills not found");
        }
        List<BillDTO> billDTOS = billEntities.stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());

        return new ResponseObject(billDTOS);
    }

    @Override
    public ResponseObject getBillHasMaxTotalPrice() {
        BillEntity billEntity = billRepository.findBillHasMaxTotalPrice();
        if (billEntity == null) {
            throw new RuntimeException("No bill has max total price");
        }
        return new ResponseObject(converter.toDTO(billEntity));
    }
}
