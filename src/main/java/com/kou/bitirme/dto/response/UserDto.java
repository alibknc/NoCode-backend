package com.kou.bitirme.dto.response;

import com.kou.bitirme.data.entity.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class UserDto {

    private UUID id;

    private String name;

    private String email;

    private UserType type;

    private String token;

}
