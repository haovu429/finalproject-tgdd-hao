/**
 * Hao Vu - haovu961@gmail.com - Jul 27, 2022
 */
package com.fsoft.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BillDetailDTO {
	private long id;
	private String productImage;

	@NotEmpty(message = "product name can not empty")
	private String productName;

	@NotNull(message = "Price can not empty")
	@Min(value = 0)
	private int originPrice;

	@NotNull(message = "discount price can not empty")
	@Min(value = 0)
	private int salePrice;

	@NotNull(message = "Bill ID can not empty")
	private long billId;

}
