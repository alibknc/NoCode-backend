package com.kou.bitirme.dto.response;

import com.kou.bitirme.data.entity.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

    private String name;

    private String email;

    private UserType type;
}
