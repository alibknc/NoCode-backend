package com.kou.bitirme.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ProjectDto implements Serializable {

    private UUID userId;

    private String title;

    private String apiKey;

    private List<TableDto> tables;
}