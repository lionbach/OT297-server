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

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@Getter
@Setter
@SQLDelete(sql = "UPDATE categories SET deleted = true where id=?")
@Where(clause = "deleted=false")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "timestamps", nullable = false)
    @CreationTimestamp
    private Timestamp timestamps;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;

    public CategoryEntity(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
