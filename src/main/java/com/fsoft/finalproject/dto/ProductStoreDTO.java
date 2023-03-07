package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ProductStoreDTO {
  private long id;
  @NotNull private long storeId;
  @NotNull private long productId;
  @NotNull private long quantity;
}
