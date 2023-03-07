//package com.fsoft.finalproject.converter;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.fsoft.finalproject.dto.RoleDTO;
//import com.fsoft.finalproject.dto.UserDTO;
//import com.fsoft.finalproject.entity.RoleEntity;
//import com.fsoft.finalproject.entity.UserEntity;
//import com.fsoft.finalproject.repository.RoleRepository;
//
//@Component
//public class UserConverter {
//
//  ModelMapper mapper = new ModelMapper();
//
//  @Autowired private RoleRepository roleRepository;
//
//  public UserDTO toDTO(UserEntity userEntity) {
//    UserDTO user = mapper.map(userEntity, UserDTO.class);
//    List<RoleDTO> role =
//        mapper.map(userEntity.getRoles(), new TypeToken<List<RoleDTO>>() {}.getType());
//    user.setRole(role);
//
//    return user;
//  }
//
//  public UserEntity toEntity(UserDTO userDTO) {
//    UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
//    List<RoleDTO> roleDTOs = userDTO.getRole();
//    for (RoleDTO item : roleDTOs) {
//      Optional<RoleEntity> role = roleRepository.findById(item.getCode());
//      if (role.isPresent()) {
//        List<RoleEntity> list = userEntity.getRoles();
//        list.add(role.get());
//        userEntity.setRoles(list);
//      }
//    }
//    return userEntity;
//  }
//}
