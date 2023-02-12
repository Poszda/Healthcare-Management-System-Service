package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.UserDTO;
import com.hmss.springbootserver.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setType(user.getType());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public static UserDTO toUserDTONoPassword(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setType(user.getType());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(null);
        return userDTO;
    }
}
