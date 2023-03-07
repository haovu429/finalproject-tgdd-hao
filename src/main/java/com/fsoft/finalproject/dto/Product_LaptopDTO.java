package com.fsoft.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product_LaptopDTO {

  @NotNull private ProductDTO productDTO;

  @NotNull private LaptopDTO laptopDTO;

}
