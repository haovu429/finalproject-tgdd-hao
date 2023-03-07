/** Hao Vu - haovu961@gmail.com - Jul 28, 2022 - 11:31:20 PM */
package com.fsoft.finalproject.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vote")
public class VoteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "guest_name", columnDefinition = "VARCHAR(50)")
  private String guestName;

  @Column(name = "guest_phone", columnDefinition = "VARCHAR(12)")
  private String guestPhone;

  @Column(name = "content", columnDefinition = "TEXT")
  private String content;

  @Column(name = "rate", columnDefinition = "FLOAT")
  private float rate;

  @Min(value = 0, message = "like number can not less then 0")
  @Column(name = "num_like")
  private int numLike;

  @Min(value = 0, message = "unlike number can not less then 0")
  @Column(name = "num_unlike")
  private int numUnlike;

  @Column(name = "time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date time;

  @Column(name = "image", columnDefinition = "VARCHAR(500)")
  private String image;

  @ManyToOne()
  @JoinColumn(name = "product_id", nullable = false)
  private ProductEntity productEntity;

  @OneToMany(mappedBy = "voteEntity", cascade = CascadeType.ALL)
  private List<CommentEntity> commentEntities = new ArrayList<>();
}
