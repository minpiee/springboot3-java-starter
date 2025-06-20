package com.springboot.service;

import com.springboot.dto.CommentRequestDTO;
import com.springboot.dto.CommentResponseDTO;
import com.springboot.entity.Comment;
import com.springboot.entity.Post;
import com.springboot.repository.CommentRepository;
import com.springboot.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

  private final CommentRepository commentRepository;
  private final PostRepository postRepository;

  @Autowired
  public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
    this.commentRepository = commentRepository;
    this.postRepository = postRepository;
  }

  public List<CommentResponseDTO> getAllComments(Long postId) {
    List<Comment> commentList = commentRepository.findByPostId(postId);

    // Entity → DTO 변환 방식 1:
    return commentList.stream().map(CommentResponseDTO::toDTO).collect(Collectors.toList());

    // Entity → DTO 변환 방식 2:
    // return commentList.stream().map(comment -> CommentResponseDTO.toDTO(comment)).collect(Collectors.toList());

    // Entity → DTO 변환 방식 3:
    // return commentList.stream().map(comment -> new CommentResponseDTO(comment.getId(), comment.getPost().getId(), comment.getNickname(), comment.getContent(), comment.getUpdatedAt())).collect(Collectors.toList());
  }

  @Transactional
  public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
    Post post = postRepository.findById(commentRequestDTO.getPostId()).orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. ID=" + commentRequestDTO.getPostId()));

    // DTO → Entity
    Comment comment = commentRequestDTO.toEntity(post);
    commentRepository.save(comment);

    // Entity → DTO
    return CommentResponseDTO.toDTO(comment);
  }
}