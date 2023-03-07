/** Hao Vu - haovu961@gmail.com - Jul 28, 2022 - 10:31:06 PM */
package com.fsoft.finalproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class PhoneEntity {

  @Id
  @Column(name = "id")
  private long id;

  @Column(name = "screen")
  private String screen;

  @Column(name = "camera")
  private String camera;

  @Column(name = "font_camera")
  private String fontCamera;

  @Column(name = "cpu")
  private String cpu;

  @Column(name = "ram")
  private String ram;

  @Column(name = "memory")
  private String memory;

  @Column(name = "chip")
  private String chip;

  @Column(name = "phone_jack")
  private String phoneJack;

  @Column(name = "charge")
  private String charge;

  @Column(name = "power")
  private String power;

  @Column(name = "size")
  private String size;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "id")
  private ProductEntity productEntity;
}
