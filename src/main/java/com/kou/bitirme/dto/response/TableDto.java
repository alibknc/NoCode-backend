package com.kou.bitirme.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
public class TableDto implements Serializable {

    private String title;

    private UUID projectId;

}
