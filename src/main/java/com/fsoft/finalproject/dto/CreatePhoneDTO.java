package com.fsoft.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePhoneDTO {
    @Valid
    private ProductDTO product;

    @Valid
    private PhoneDTO phone;
}
