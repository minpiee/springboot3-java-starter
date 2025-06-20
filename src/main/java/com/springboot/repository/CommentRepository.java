package com.springboot.repository;

import com.springboot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  @Query(value = "SELECT * FROM comment WHERE post_id = :postId", nativeQuery = true)
  List<Comment> findByPostId(Long postId);
}