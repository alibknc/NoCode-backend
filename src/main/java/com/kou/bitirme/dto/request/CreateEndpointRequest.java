package com.kou.bitirme.dto.request;

import com.kou.bitirme.data.entity.enums.RestType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateEndpointRequest {

    @Enumerated(EnumType.STRING)
    private RestType type;

    private String query;

    private String url;

    private String table;

    private String projectId;

    private String userId;

}
