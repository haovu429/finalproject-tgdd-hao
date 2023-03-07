package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemInCart {
  private Long productId;
  private Integer quantity;
}
