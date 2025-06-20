package com.springboot.controller;

import com.springboot.dto.PostRequestDTO;
import com.springboot.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  // 생성자가 하나뿐인 경우, @Autowired 생략 가능 (Spring 4.3 이상)
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("/new")
  public String showCreateForm() {
    return "post/create";
  }

  @PostMapping
  public String createPost(@ModelAttribute PostRequestDTO postRequestDTO) {
    Long id = postService.createPost(postRequestDTO);
    return "redirect:/posts/" + id;
  }
}