package com.fsoft.finalproject.dto;

import com.fsoft.finalproject.utils.Constant;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VoteDTO {
  private long id;
  @NotEmpty(message = "guest can not null")
  private String guestName;

  @NotEmpty(message = "Phone number can not empty")
  @Pattern(regexp = Constant.PHONE_REGEX, message = "phone format is invalid")
  private String guestPhone;

  @NotEmpty(message = "content can not empty")
  @Length(max = 250, message = "content is too long")
  private String content;
  private float rate;
  private int numLike;
  private int numUnlike;
  private Date time;
  private String image;

//  @NotEmpty(message = "product can not empty")
//  private long productId;
  private String productCode;
  //private List<CommentDTO> comments = new ArrayList<>();

}
