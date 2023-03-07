package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.converter.Converter;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.dto.RoleDTO;
import com.fsoft.finalproject.dto.UserDTO;
import com.fsoft.finalproject.entity.RoleEntity;
import com.fsoft.finalproject.entity.UserEntity;
import com.fsoft.finalproject.repository.RoleRepository;
import com.fsoft.finalproject.repository.UserRepository;
import com.fsoft.finalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private Converter converter;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired private RoleRepository roleRepository;


  ModelMapper mapper = new ModelMapper();

  public boolean checkEmailExists(String email) {
    UserEntity user = userRepository.getUserByEmail(email);
    if (user == null) {
      return true;
    } else {

      return false;
    }
  }

  @Override
  public ResponseObject save(UserDTO userDTO) {
//  hash password
    userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

    // Cast DTO --> Entity
    UserEntity userEntity = converter.toEntity(userDTO);

    // Check Role Input, Neu K tim thay Role ==> Cancel
    if (userEntity.getRoles().size() == 0) {
      throw new RuntimeException("Role not found");
    }

    // Check Email Exists
    if (!checkEmailExists(userEntity.getEmail())) {
      throw new RuntimeException("Email already exists");
    }

    // Luu vao dataabase
    userEntity = userRepository.save(userEntity);
    return new ResponseObject(HttpStatus.OK.value(), "Create user success", converter.toDTO(userEntity));

  }

  @Override
  public ResponseObject update(long id, UserDTO userDTO) {

    UserEntity userEntity = converter.toEntity(userDTO);

    if (userEntity.getRoles().size() == 0) {
      throw new RuntimeException("Role not found");
    } else {
      UserEntity newUser = userRepository.findById(id).map(user -> {
        user.setName(userEntity.getName());
        user.setAvatar(userEntity.getAvatar());
        user.setPassword(userEntity.getPassword());
        user.setRoles(userEntity.getRoles());
        return userRepository.save(user);
      }).orElse(null);

      if (newUser == null) {
        throw new RuntimeException("User not found");
      } else {
        return new ResponseObject(HttpStatus.OK.value(), "Update user success", converter.toDTO(newUser));
      }
    }

  }

  @Override
  public ResponseObject delete(long id) {
    boolean exists = userRepository.existsById(id);
    if (exists) {
      userRepository.deleteById(id);
      return new ResponseObject(HttpStatus.OK.value(), "Delete user success", "");
    } else {
      throw new RuntimeException("User not found");
    }

  }

  @Override
  public ResponseObject getAll() {
    // Lay Tat Ca UserEntity
    List<UserEntity> userEntitys = userRepository.findAll();
    // Tao UserDTO de tra ve Json
    List<UserDTO> userDTOs = new ArrayList<>();

    // Cast UserEntity --> UserDTO
    for (UserEntity i : userEntitys) {
      UserDTO user = converter.toDTO(i);
      userDTOs.add(user);
    }
    return new ResponseObject(HttpStatus.OK.value(), "Get all user success", userDTOs);
  }

  @Override
  public ResponseObject getUserByAccount(String email, String password) {
    // TODO Auto-generated method stub
    UserEntity userEntity = userRepository.getUserByAccount(email, password);
    if (userEntity != null) {
      return new ResponseObject(HttpStatus.OK.value(), "Get user success", converter.toDTO(userEntity));
    } else {
      throw new RuntimeException("User not found");
    }
  }

}
