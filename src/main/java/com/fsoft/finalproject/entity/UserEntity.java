package com.fsoft.finalproject.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email", columnDefinition = "VARCHAR(50)")
  private String email;

  @Column(name = "name", columnDefinition = "VARCHAR(50)")
  private String name;

  @Column(name = "password", columnDefinition = "VARCHAR(100)")
  private String password;

  @Column(name = "avatar", columnDefinition = "VARCHAR(500)")
  private String avatar;

  @Column(name = "is_active")
  private Boolean isActive;

  //
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_role",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<RoleEntity> roles = new ArrayList<>();

}
