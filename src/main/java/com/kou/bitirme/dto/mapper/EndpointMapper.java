package com.kou.bitirme.dto.mapper;

import com.kou.bitirme.data.entity.Endpoint;
import com.kou.bitirme.data.entity.User;
import com.kou.bitirme.dto.request.CreateEndpointRequest;
import com.kou.bitirme.dto.request.CreateUserRequest;
import com.kou.bitirme.dto.response.EndpointDto;
import com.kou.bitirme.dto.response.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(implementationName = "EndpointMapperImpl", componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EndpointMapper {

    EndpointDto toEndpointDto(Endpoint endpoint);

    Endpoint toEndpoint(CreateEndpointRequest endpoint);

}
