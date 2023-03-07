package com.fsoft.finalproject.repository;

import java.util.List;

import com.fsoft.finalproject.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fsoft.finalproject.entity.VoteEntity;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

  @Query("SELECT c FROM VoteEntity c WHERE c.productEntity.productCode = :param1")
  public Page<VoteEntity> getVoteByProductCode(@Param("param1") String code, Pageable pageable);

  @Query("SELECT c FROM VoteEntity c WHERE c.productEntity.id = :param1 AND c.rate <> 0")
  public Page<VoteEntity> getVoteByProductId(@Param("param1") long id, Pageable pageable);

  @Query("SELECT c FROM VoteEntity c WHERE c.productEntity.id = :param1 AND c.rate = 0")
  public Page<VoteEntity> getCommentByProductId(@Param("param1") long id, Pageable pageable);
}
