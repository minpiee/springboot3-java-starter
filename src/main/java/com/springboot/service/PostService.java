package com.springboot.service;

import com.springboot.dto.PostRequestDTO;
import com.springboot.dto.PostResponseDTO;
import com.springboot.entity.Post;
import com.springboot.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

  private final PostRepository postRepository;

  // 생성자가 하나뿐인 경우, @Autowired 생략 가능 (Spring 4.3 이상)
  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Transactional
  public Long createPost(PostRequestDTO postRequestDTO) {
    // DTO → Entity
    Post post = postRequestDTO.toEntity();

    postRepository.save(post);
    return post.getId();
  }

  public PostResponseDTO getPost(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. ID=" + postId));
    return PostResponseDTO.toDTO(post);
  }
}