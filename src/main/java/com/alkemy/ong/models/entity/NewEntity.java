package com.alkemy.ong.models.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "news")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE news SET deleted = true where id=?")
@Where(clause = "deleted=false")
public class NewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "image", nullable = false)
    private String image;

    /*
    @Column(name = "timestamps", nullable = false)
    @CreationTimestamp
    private Timestamp timestamps;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;
*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "category_id", // indica con que columna vamos a relacionar en category
            insertable = false, updatable = false // esto permite que la columna no se cree
    )
    private CategoryEntity category;

    // columna de la relacion category_id
    @Column(name = "category_id", nullable = false)
    private Long categoryId;


    private boolean deleted = Boolean.FALSE;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp timestamp;


    public NewEntity(String name, String content, String image, Long categoryId) {
        this.name = name;
        this.content = content;
        this.image = image;
        this.categoryId = categoryId;
    }
//    @PrePersist
//    void persist() {
//        setTimestamps(LocalDateTime.now());
//    }
}
