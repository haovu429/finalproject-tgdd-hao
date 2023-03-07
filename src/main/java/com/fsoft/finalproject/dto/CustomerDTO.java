/**
 * Hao Vu - haovu961@gmail.com - Jul 28, 2022
 */
package com.fsoft.finalproject.dto;

import com.fsoft.finalproject.utils.Constant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.mapstruct.Mapping;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
	private long id;
	@NotEmpty(message = "name can not empty")
	@Length(min = 3, max = 50, message = "name exceed 50 characters")
	private String fullName;

	@NotEmpty(message = "phone can not empty")
	@Pattern(regexp = Constant.PHONE_REGEX, message = "phone format is invalid")
	private String phone;

	@NotEmpty(message = "email can not empty")
	@Pattern(regexp = Constant.EMAIL_REGEX, message = "email format is invalid")
	private String email;

	@NotEmpty(message = "gender can not empty")
	@Length(min = 2, max = 6)
	private String gender;

	//private List<AddressDTO> addressDTOs = new ArrayList<>();



}
