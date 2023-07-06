package com.kou.bitirme.data.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Project {

    @Id
    @GeneratedValue
    @Column(length = 36)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String schema;

    @Column(nullable = false)
    private String apiKey;

}
