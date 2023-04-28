package com.kou.bitirme.dto.response;

import com.kou.bitirme.data.entity.enums.RestType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EndpointDto {

    private RestType type;

    private String query;

    private String url;

    private String projectId;

    private String userId;

}
