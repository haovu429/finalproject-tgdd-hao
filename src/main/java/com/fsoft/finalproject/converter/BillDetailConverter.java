//package com.fsoft.finalproject.converter;
//
//import com.fsoft.finalproject.dto.BillDetailDTO;
//import com.fsoft.finalproject.entity.BillDetailEntity;
//import com.fsoft.finalproject.entity.BillEntity;
//import com.fsoft.finalproject.repository.BillDetailRepository;
//import com.fsoft.finalproject.repository.BillRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BillDetailConverter {
//
//  ModelMapper mapper = new ModelMapper();
//
//  @Autowired private BillDetailRepository billDetailRepository;
//
//  @Autowired private BillRepository billRepository;
//
//  public BillDetailDTO toDTO(BillDetailEntity billDetailEntity) {
//    BillDetailDTO billDetail = mapper.map(billDetailEntity, BillDetailDTO.class);
//    billDetail.setBillId(billDetailEntity.getBillEntity().getId());
//    return billDetail;
//  }
//
//  public BillDetailEntity toEntity(BillDetailDTO billDetailDTO) {
//    BillDetailEntity billDetailEntity = mapper.map(billDetailDTO, BillDetailEntity.class);
//    BillEntity bill = billRepository.findOneById(billDetailDTO.getBillId());
//    billDetailEntity.setBillEntity(bill);
//    return billDetailEntity;
//  }
//}
