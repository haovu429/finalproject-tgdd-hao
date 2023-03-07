package com.fsoft.finalproject.dto;

import com.fsoft.finalproject.entity.ProvinceEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {
	private long id;

	@NotNull(message = "Ward can not empty")
	private long wardId;

	@NotEmpty(message = "numHouse can not empty")
	@Length(max = 100, message = "length is exceeded 100 characters")
	private String numHouse;
}
