package com.alkemy.ong.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "testimonials")
@SQLDelete(sql = "UPDATE testimonials SET soft_delete = true where id=?")
@Where(clause = "soft_delete=false")
@NoArgsConstructor
public class TestimonialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp", nullable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete", nullable = false)
    private Boolean softDelete = Boolean.FALSE;

    public TestimonialEntity(String name, String image, String content) {
        this.name = name;
        this.image = image;
        this.content = content;
    }
}
