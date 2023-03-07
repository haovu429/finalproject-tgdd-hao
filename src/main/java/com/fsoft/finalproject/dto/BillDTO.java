/**
 * Hao Vu - haovu961@gmail.com - Jul 27, 2022
 */
package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BillDTO {
  private long id;
  private long billCode;
  private String status;
  private String storeAddress;
  private long totalPrice;
  private String customerName;
  @Pattern(regexp = "^(\\+84|0)\\d{9,10}$", message = "Số điện thoại không hợp lệ")
  private String customerPhone;
  private String receiverAddress;
  private Date receivingTime;
  private String note;
  //private List<BillDetailDTO> billDetailDTOs = new ArrayList<>();
}
