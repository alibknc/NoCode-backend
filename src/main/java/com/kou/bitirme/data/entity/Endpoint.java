package com.kou.bitirme.data.entity;

import com.kou.bitirme.data.entity.enums.RestType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Endpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RestType type;

    @Column(nullable = false)
    private String query;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String projectId;

}
