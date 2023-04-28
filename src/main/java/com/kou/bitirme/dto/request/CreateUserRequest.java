package com.kou.bitirme.dto.request;

import com.kou.bitirme.data.entity.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Builder
public class CreateUserRequest {

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

}
