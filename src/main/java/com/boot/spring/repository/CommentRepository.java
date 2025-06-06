package com.boot.spring.repository;

import com.boot.spring.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  @Query(value = "SELECT * FROM comment WHERE post_id = :postId", nativeQuery = true)
  List<Comment> findByPostId(Long postId);
}