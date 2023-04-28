package com.kou.bitirme.dto.mapper;

import com.kou.bitirme.data.entity.User;
import com.kou.bitirme.dto.request.CreateUserRequest;
import com.kou.bitirme.dto.response.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(implementationName = "UserMapperImpl", componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(CreateUserRequest user);

}
