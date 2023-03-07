package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class LaptopDTO {
  private long id;
  @Length(max = 70)
  private String cpu;

  @Length(max = 10)
  private String ram;

  @Length(max = 50)
  private String hardDrive;

  @Length(max = 50)
  private String screen;

  @Length(max = 50)
  private String graphicsCard;

  @Length(max = 50)
  private String connectionPort;

  @Length(max = 50)
  private String design;

  @Length(max = 50)
  private String sizeWeight;

  private Date releaseDate;

}
