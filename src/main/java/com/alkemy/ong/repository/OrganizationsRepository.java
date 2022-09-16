package com.alkemy.ong.repository;

import com.alkemy.ong.models.entity.OrganizationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationsRepository extends JpaRepository<OrganizationsEntity, Long> {
}
