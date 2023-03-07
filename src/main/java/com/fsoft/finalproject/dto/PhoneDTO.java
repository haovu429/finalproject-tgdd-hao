package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PhoneDTO {
    private long id;

    @NotBlank(message = "Don't blank")
    private String screen;

    @NotBlank(message = "Don't blank")
    private String camera;
    private String fontCamera;
    private String cpu;
    private String ram;
    private String memory;
    private String chip;
    private String phoneJack;
    private String charge;
    private String power;
    private String size;

}
