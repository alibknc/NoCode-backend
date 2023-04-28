package com.kou.bitirme.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CreateProjectRequest {

    private UUID userId;

    private String title;

}
