package com.fsoft.finalproject.dto;

import com.fsoft.finalproject.utils.Constant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class StoreDTO {
    private long id;
    @NotBlank(message = "Don't blank")
    @Size(min = 1, max = 50, message = "Size must be between 1 and 50")
    private String name;

    @NotBlank(message = "Don't blank")
    @Pattern(regexp = Constant.PHONE_REGEX, message = "Phone number is invalid")
    private String phone;
    private String fax;

    @NotBlank(message = "Don't blank")
    @Pattern(regexp = Constant.EMAIL_REGEX, message = "Email is invalid")
    private String email;
    private String image;
    private String description;
    private String openHour;
    private float Iat;
    private float Ing;
    private long addressId;
}
