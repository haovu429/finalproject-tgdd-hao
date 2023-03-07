package com.fsoft.finalproject.dto;

import com.fsoft.finalproject.utils.Constant;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
  private long id;

  @Pattern(regexp = Constant.EMAIL_REGEX, message = "Email is invalid")
  private String email;

  @NotBlank(message = "Don't blank")
  @Size(min = 1, max = 50, message = "Size must be between 1 and 50")
  private String name;

  @NotBlank(message = "Don't blank")
  @Size(min = 8, max = 32, message = "Password must be between 8 and 32")
  private String password;

  private String avatar;

  private Boolean isActive;
  private List<String> roleCodes = new ArrayList<>();
}
