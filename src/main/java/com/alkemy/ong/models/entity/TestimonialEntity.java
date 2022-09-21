package com.alkemy.ong.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@Table(name = "testimonial")
@SQLDelete(sql = "UPDATE testimonial SET soft_delete = true where id=?")
@Where(clause = "soft_delete=false")
public class TestimonialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id_testimonials")
    private Long idTestimonials;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "soft_delete", nullable = false)
    private Boolean softDelete = Boolean.FALSE;

    public TestimonialEntity() {
    }
}
