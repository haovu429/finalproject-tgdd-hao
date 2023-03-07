package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CustomerAddressDTO {
  private long id;

  @NotNull(message = "ward can not empty")
  private long wardId;

  @NotEmpty(message = "num_house can not empty")
  @Length(max = 150, message = "num_house is too long")
  private String numHouse;

  @NotNull(message = "customer id can not empty")
  private long customerId;

}
