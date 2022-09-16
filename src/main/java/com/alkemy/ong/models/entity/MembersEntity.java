package com.alkemy.ong.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name="members")
@Setter
@Getter
@SQLDelete(sql = "UPDATE organizations SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class MembersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    private String facebookUrl;

    private String instagramUrl;

    private String linkedinUrl;

    @NotNull
    @Column(nullable = false)
    private String image;

    private String description;

    private boolean deleted = Boolean.FALSE;

    @NotNull
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp timestamps;

}


/*
COMO desarrollador
QUIERO agregar la entidad Member
PARA representar en la implementación la estructura de datos

Criterios de aceptación:
Nombre de tabla: members. Campos:
name: VARCHAR NOT NULL
facebookUrl: VARCHAR NULLABLE
instagramUrl: VARCHAR NULLABLE
linkedinUrl: VARCHAR NULLABLE
image: VARCHAR NOT NULL
description: VARCHAR NULLABLE
timestamps y softDelete
 */