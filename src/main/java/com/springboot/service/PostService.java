package com.springboot.service;

import com.springboot.dto.PostRequestDTO;
import com.springboot.dto.PostResponseDTO;
import com.springboot.entity.Post;
import com.springboot.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

  public List<PostResponseDTO> getAllPosts() {
    List<Post> posts = postRepository.findAll();

    // Entity → DTO 변환 방식 1:
    return posts.stream().map(PostResponseDTO::toDTO).collect(Collectors.toList());

    // Entity → DTO 변환 방식 2:
    // return posts.stream().map(post -> PostResponseDTO.toDTO(post)).collect(Collectors.toList());

    // Entity → DTO 변환 방식 3:
    // return posts.stream().map(post -> new PostResponseDTO(post.getId(), post.getTitle(), post.getContent(), post.getUpdatedAt())).collect(Collectors.toList());
  }

  @Transactional
  public Long updatePost(PostRequestDTO postRequestDTO) {
    // DB에 실제로 존재하는 값인지 먼저 확인
    Post post = postRepository.findById(postRequestDTO.getId()).orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. ID=" + postRequestDTO.getId()));

    // 제목과 내용을 업데이트
    post.setTitle(postRequestDTO.getTitle());
    post.setContent(postRequestDTO.getContent());

    // 별도로 save()를 호출하지 않아도 됨
    // @Transactional 덕분에 트랜잭션이 끝날 때 JPA가 변경된 내용을 감지하고
    // 자동으로 DB에 update 쿼리를 실행함 (JPA의 변경 감지 기능)
    return post.getId();
  }
}