package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressResponseDTO {
  private long id;
  private ProvinceDTO province;
  private DistrictDTO district;
  private WardDTO ward;
  private String numHouse;

}
