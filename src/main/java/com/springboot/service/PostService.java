package com.springboot.service;

import com.springboot.dto.PostRequestDTO;
import com.springboot.entity.Post;
import com.springboot.repository.PostRepository;
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
}