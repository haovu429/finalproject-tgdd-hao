package com.fsoft.finalproject.dto;

import com.fsoft.finalproject.utils.Constant;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
	private long id;
	@NotNull
	@Size(min = 1, max = 50, message = "Size must be between 1 and 50")
	private String guestName;

	@NotEmpty(message = "Phone number can not empty")
	@Pattern(regexp = Constant.PHONE_REGEX, message = "Phone number is invalid")
	private String guestPhone;

	@NotEmpty(message = "content can not empty")
	@Length(max = 250, message = "content is too long")
	private String content;
	private int numLike;
	private Date time;
	private String image;

	private long voteId;
}
