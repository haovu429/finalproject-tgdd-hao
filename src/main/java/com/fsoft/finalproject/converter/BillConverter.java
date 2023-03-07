//package com.fsoft.finalproject.converter;
//
//import com.fsoft.finalproject.dto.BillDTO;
//import com.fsoft.finalproject.dto.BillDetailDTO;
//import com.fsoft.finalproject.entity.BillDetailEntity;
//import com.fsoft.finalproject.entity.BillEntity;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class BillConverter implements ConvertHelper<BillEntity, BillDTO> {
//
//  @Override
//  public BillDTO toDTO(BillEntity entity) {
//    BillDTO billDTO = new BillDTO();
//    billDTO.setId(entity.getId());
//    billDTO.setBillCode(entity.getBillCode());
//    billDTO.setStatus(entity.getStatus());
//    billDTO.setStoreAddress(entity.getStoreAddress());
//    billDTO.setTotalPrice(entity.getTotalPrice());
//    billDTO.setCustomerName(entity.getCustomerName());
//    billDTO.setCustomerPhone(entity.getCustomerPhone());
//    billDTO.setReceiverAddress(entity.getReceiverAddress());
//    billDTO.setReceivingTime(entity.getReceivingTime());
//    billDTO.setBillDetailDTOs(entity.getBillDetails());
//    return null;
//  }
//
//  @Override
//  public BillEntity toEntity(BillDTO dto) {
//    return null;
//  }
//}
