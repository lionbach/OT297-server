package com.alkemy.ong.models.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "slides")
@Getter
@Setter
@SQLDelete(sql = "UPDATE slides SET deleted = true Where id=?")
@Where(clause = "deleted=false")
public class SlideEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String imageUrl;

    @NotNull
    @NotEmpty
    private String text;


    // definimos la relacion slice-organization
    // no se va a crear esta columna en mysql
    // la columna de la relacion la definimos despues
    // tambien sirve para obtener, en java, los datos de la organizacion en el slice
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "organization_id", // indica con que columna vamos a relacionar en organization
            insertable = false, updatable = false // esto permite que la columna no se cree
    )
    private OrganizationEntity organization;

    // columna de la relacion organization_id
    @Column(name = "organization_id", nullable = false)
    private Long organizationId;


    private boolean deleted = Boolean.FALSE;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp timestamp;

}
