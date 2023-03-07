package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
  private long id;

  private Date date;

  @NotEmpty(message = "total of order can not empty")
  @Min(value = 0)
  private int total;

  private String orderCode;
  private String status;

  @NotEmpty(message = "store id can not empty")
  private long storeId;

  @NotEmpty(message = "customer id can not empty")
  private long customerId;

  private String note;

  private long paymentId;
  // private List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
}
