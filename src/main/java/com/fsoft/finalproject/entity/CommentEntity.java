/** Hao Vu - haovu961@gmail.com - Jul 28, 2022 - 11:04:04 PM */
package com.fsoft.finalproject.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class CommentEntity {

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

  @Column(name = "num_like")
  private int numLike;

  @Column(name = "time")
  @Temporal(TemporalType.DATE)
  private Date time;

  @Column(name = "image")
  private String image;

  @ManyToOne()
  @JoinColumn(name = "vote_id", nullable = false)
  private VoteEntity voteEntity;
}
