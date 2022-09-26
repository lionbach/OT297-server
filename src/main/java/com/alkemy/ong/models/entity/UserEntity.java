package com.alkemy.ong.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
@SQLDelete(sql = "UPDATE user SET soft_delete = true where id=?")
@Where(clause = "soft_delete=false")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(name = "photo")
    private String photo;

    @Column(name = "timestamp", nullable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete", nullable = false)
    private Boolean softDelete = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<RoleEntity> roleEntityId;

    public UserEntity(String firstName, String lastName, String email, String password, String photo,
                      Timestamp timestamp, Set<RoleEntity> roleEntity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.timestamp = timestamp;
        this.roleEntityId = roleEntity;
    }
}
