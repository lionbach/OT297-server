package com.alkemy.ong.repository;

import com.alkemy.ong.models.entity.CommentEntity;
import com.alkemy.ong.models.response.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByNewsId(Long id);
}
