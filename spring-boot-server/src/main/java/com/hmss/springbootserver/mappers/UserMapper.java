package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.AppointmentDTO;
import com.hmss.springbootserver.DTOs.UserDTO;
import com.hmss.springbootserver.entities.User;

import java.util.ArrayList;

public class UserMapper {
    public static UserDTO userToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserType(user.getUserType());
        var x = PatientMapper.patientToDtoNoUser(user.getPatient());
        userDTO.setPatient(x);
        return userDTO;
    }

    public static UserDTO userToDTORestricted(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserType(user.getUserType());
        userDTO.setPatient(null);
        return userDTO;
    }

    //CUSTOM METHOD
    public static UserDTO userToDtoDepth1(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserType(user.getUserType());
        userDTO.setPatient(PatientMapper.patientToDtoRestricted(user.getPatient()));
        return userDTO;
    }

    //HERE ADD ALL userToDtoNo___*attribute name* ___

    public static UserDTO userToDtoNoPatient(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserType(user.getUserType());
        userDTO.setPatient(null);
        return userDTO;
    }


}
