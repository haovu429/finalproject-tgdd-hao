package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailDTO {
  private long id;

  @NotEmpty(message = "quantity is not empty")
  @Min(value = 0, message = "quantity is not less than 0")
  private int quantity;

  @NotEmpty(message = "price is not empty")
  @Min(value = 0, message = "price is not less than 0")
  private long price;

  private long orderId;

  @NotEmpty(message = "product can not empty")
  private long productId;

}
