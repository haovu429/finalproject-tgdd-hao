package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
  private long id;

  @NotBlank(message = "Don't blank")
  private String productName;

  @Length(min = 4, message = "Product code longer than 4")
  @NotBlank(message = "Price is mandatory")
  private String productCode;

  @NotBlank(message = "Don't blank")
  @Size(min = 1, max = 50, message = "Size must be between 1 and 50")
  private String category;

  private String description;

  @NotNull
  @Min(value = 0, message = "Price can not be less than 0")
  private int price;

  private String os;

  @NotNull(message = "Manufacturer id is mandatory")
  private long manufacturerId;

  private int warranty;
  private String standardKit;
  private String advantages;
  private String disadvantages;
  private String included;
  private String article;
  private int numSidePhoto;
  private int numView;
  // private List<ImageDTO> images = new ArrayList<>();

  private List<PromotionDTO> promotions = new ArrayList<>();

}
