package com.kou.bitirme.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CreateTableRequest {

    private String title;

    private UUID projectId;

    private List<Map<String, String>> columns;

}
