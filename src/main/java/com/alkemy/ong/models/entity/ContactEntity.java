package com.alkemy.ong.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

/*
COMO usuario administrador
QUIERO almacenar los datos enviados al completar un Formulario de Contacto
PARA tener un seguimiento de los mismos

Criterios de aceptación:
Al completar el Formulario de Contacto,
se almacenará en la base de datos.
 Nombre de tabla: contacts.
 Contendrá los campos name, phone, email, message, deletedAt (utilizada para soft delete)
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
@Getter
@Setter
@SQLDelete(sql = "UPDATE contacts SET deleted = true where id=?")
@Where(clause = "deleted=false")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "message")
    private String message;

    @Column(name = "timestamps", nullable = false)
    @CreationTimestamp
    private Timestamp timestamps;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;

    public ContactEntity(String name, String phone, String email, String message) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.message = message;
    }

}
