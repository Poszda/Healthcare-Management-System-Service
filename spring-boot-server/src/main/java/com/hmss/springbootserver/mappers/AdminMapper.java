package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.admin.AdminDTO;
import com.hmss.springbootserver.DTOs.doctor.DoctorDTO;
import com.hmss.springbootserver.entities.Admin;
import com.hmss.springbootserver.entities.Doctor;
import com.hmss.springbootserver.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("AdminMapper")
@Mapper
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);
    @Named("adminToAdminDTO")
    @Mapping(source = "user", target = "userId", qualifiedByName = "mapUserId")
    AdminDTO adminToAdminDTO(Admin admin);

    // Map userId from User entity
    @Named("mapUserId")
    default Long mapUserId(User user) {
        return user != null ? user.getId() : null;
    }
}
