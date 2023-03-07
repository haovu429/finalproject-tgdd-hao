package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ManufacturerDTO {
  private long id;

  @NotEmpty(message = "manufacture's name can not empty")
  private String name;
}
