package com.fsoft.finalproject.dto;

import lombok.*;

import javax.validation.Valid;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLapTopDTO {

    @Valid
    private ProductDTO product;

    @Valid
    private LaptopDTO laptop;
}
