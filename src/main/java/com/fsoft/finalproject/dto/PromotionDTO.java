package com.fsoft.finalproject.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PromotionDTO {
  private long id;

  @NotNull
  private Date startDay;
  @NotNull
  private Date endDate;

  @NotNull
  private int discount;
  private String description;

  @NotNull
  @Size(min = 1, max = 10)
  private String promoCode;

  private String type;

  // private List<ProductDTO> products = new ArrayList<>();
}
