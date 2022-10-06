package com.alkemy.ong.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activities")
@Getter
@Setter
@SQLDelete(sql = "UPDATE activities SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "timestamps", nullable = false)
    @CreationTimestamp
    private Timestamp timestamps;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;

    public ActivityEntity(String name, String content, String image) {
        this.name = name;
        this.content = content;
        this.image = image;
    }

    public ActivityEntity() {

    }

//    @PrePersist
//    void persist() {
//        setTimestamps(LocalDateTime.now());
//    }
}
