package com.alkemy.ong.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="organizations")
@Setter
@Getter
@SQLDelete(sql = "UPDATE organizations SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String image;

    private String address;

    private String phone;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false, columnDefinition="TEXT")
    private String welcomeText;

    @Column(columnDefinition="TEXT")
    private String aboutUsText;

    private boolean deleted = Boolean.FALSE;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp timestamps;

    public OrganizationEntity(String name, String image, String address, String phone, String email, String welcomeText, String aboutUsText) {
        this.name = name;
        this.image = image;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.welcomeText = welcomeText;
        this.aboutUsText = aboutUsText;
    }
}
