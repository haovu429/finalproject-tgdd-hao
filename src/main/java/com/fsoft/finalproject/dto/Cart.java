package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Cart {
  @NotNull(message = "product id can not empty")
  private List<ItemInCart> items;

  @Min(value = 0, message = "total is not less than 0")
  private int total;

  @NotNull(message = "customer id can not empty")
  private CustomerDTO customer;

  @NotNull(message = "address  can not empty")
  private AddressResponseDTO addressResponseDTO;

  @NotNull(message = "payment id can not empty")
  private long paymentId;
  private String note;
}
