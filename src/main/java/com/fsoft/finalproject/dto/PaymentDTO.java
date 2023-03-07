/** Hao Vu - haovu961@gmail.com - Jul 27, 2022 */
package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO {
  private long id;

  @NotBlank(message = "Don't blank")
  @Size(min = 1, max = 50, message = "Size must be between 1 and 50")
  private String name;
}
