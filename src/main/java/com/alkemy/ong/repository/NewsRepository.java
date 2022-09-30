package com.alkemy.ong.repository;

import com.alkemy.ong.models.entity.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewEntity, Long> {


}
