/** Hao Vu - haovu961@gmail.com - Jul 28, 2022 - 10:34:29 PM */
package com.fsoft.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "laptop")
public class LaptopEntity {

  @Id
  @Column(name = "id")
  private long id;

  @Column(name = "cpu")
  private String cpu;

  @Column(name = "ram")
  private String ram;

  @Column(name = "hard_drive")
  private String hardDrive;

  @Column(name = "screen")
  private String screen;

  @Column(name = "graphics_card")
  private String graphicsCard;

  @Column(name = "connection_port")
  private String connectionPort;

  @Column(name = "design")
  private String design;

  @Column(name = "size_weight")
  private String sizeWeight;

  @Column(name = "release_date")
  @Temporal(TemporalType.DATE)
  private Date releaseDate;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "id")
  private ProductEntity productEntity;

}
