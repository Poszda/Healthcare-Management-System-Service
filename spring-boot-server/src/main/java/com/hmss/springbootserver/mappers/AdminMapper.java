package com.hmss.springbootserver.mappers;

import com.hmss.springbootserver.DTOs.admin.AdminWithHospitalDTO;
import com.hmss.springbootserver.entities.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Named("AdminMapper")
@Mapper
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    @Named("toAdminWithHospitalDTO")
    @Mapping(source = "user.id", target = "userId")
    AdminWithHospitalDTO toAdminWithHospitalDTO(Admin admin);
}
