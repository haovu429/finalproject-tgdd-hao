package com.fsoft.finalproject.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fsoft.finalproject.entity.CommentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("SELECT c FROM CommentEntity c WHERE c.voteEntity.id = :param1")
    public Page<CommentEntity> getCommentByVoteId(@Param("param1") long id, Pageable pageable);

    @Query("SELECT c FROM CommentEntity c WHERE c.voteEntity.id = :param1")
    Optional<CommentEntity> getCommentByVoteId(@Param("param1") long id);
}
