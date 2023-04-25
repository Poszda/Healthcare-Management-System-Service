package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.UserDTOTest;
import com.hmss.springbootserver.entities.User;

public class UserMapperTest {
    public static UserDTOTest userToDTO(User user){
        UserDTOTest userDTO = new UserDTOTest();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserType(user.getUserType());
        var x = PatientMapperTest.patientToDtoNoUser(user.getPatient());
        userDTO.setPatient(x);
        return userDTO;
    }

    public static UserDTOTest userToDTORestricted(User user){
        UserDTOTest userDTO = new UserDTOTest();
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
    public static UserDTOTest userToDtoDepth1(User user){
        UserDTOTest userDTO = new UserDTOTest();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserType(user.getUserType());
        userDTO.setPatient(PatientMapperTest.patientToDtoRestricted(user.getPatient()));
        return userDTO;
    }

    //HERE ADD ALL userToDtoNo___*attribute name* ___

    public static UserDTOTest userToDtoNoPatient(User user){
        UserDTOTest userDTO = new UserDTOTest();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserType(user.getUserType());
        userDTO.setPatient(null);
        return userDTO;
    }



    //DTOs to Entity
    public static User userDTOtoEntity(UserDTOTest userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setPatient(PatientMapperTest.DTOtoPatientEntity(userDTO.getPatient()));
        return user;
    }


}
