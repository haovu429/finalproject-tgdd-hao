package com.fsoft.finalproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DistrictDTO {
    private long id;

    @NotNull(message = "district name can not empty")
    @Length(max = 100, message = "name exceed 100 characters")
    private String name;

    private long provinceId;
}
