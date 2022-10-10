package com.alkemy.ong.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "news_id", insertable = false, updatable = false)
    private NewEntity news;

    @Column(name = "news_id", nullable = false)
    private Long newsId;

    @Column(name = "body", nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp timestamps;

    public CommentEntity(Long userId, Long newsId, String body) {
        this.userId = userId;
        this.newsId = newsId;
        this.body = body;
    }
}
