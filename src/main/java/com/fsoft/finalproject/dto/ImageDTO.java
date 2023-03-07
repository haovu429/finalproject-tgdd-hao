package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class ImageDTO {
    private long id;

    @NotEmpty(message = "Image's link can not empty")
    private String link;

    @NotEmpty(message = "Product can not empty")
    private long productId;
}
