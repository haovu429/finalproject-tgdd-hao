package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class NewAddressDTO {

  @NotNull(message = "Ward code can not empty")
  private long wardId;

  @NotNull(message = "num_house can not empty")
  @Length(max = 150, message = "num_house is too long")
  private String numHouse;

  private long customerId;
}
