package com.springboot.controller;

import com.springboot.dto.PostRequestDTO;
import com.springboot.dto.PostResponseDTO;
import com.springboot.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

  @GetMapping("/{postId}")
  public String getPost(@PathVariable Long postId, Model model) {
    PostResponseDTO postResponseDTO = postService.getPost(postId);
    model.addAttribute("postResponseDTO", postResponseDTO);
    return "post/detail";
  }

  @GetMapping
  public String getAllPosts(Model model) {
    List<PostResponseDTO> postResponseDTOList = postService.getAllPosts();
    model.addAttribute("postResponseDTOList", postResponseDTOList);
    return "post/list";
  }

  @GetMapping("/{postId}/edit")
  public ModelAndView showUpdateForm(@PathVariable Long postId, Model model) {
    ModelAndView mav = new ModelAndView();

    PostResponseDTO postResponseDTO = postService.getPost(postId);

    if (postResponseDTO == null) {
      mav.setViewName("redirect:/posts");
      return mav;
    }

    return new ModelAndView("post/edit", "post", postResponseDTO);
  }

  @PatchMapping("/{postId}")
  public String updatePost(@PathVariable Long postId, @ModelAttribute PostRequestDTO postRequestDTO) {
    postRequestDTO.setId(postId);

    Long id = postService.updatePost(postRequestDTO);
    return "redirect:/posts/" + id;
  }

  @DeleteMapping("/{postId}")
  public String deletePost(@PathVariable Long postId) {
    postService.deletePost(postId);
    return "redirect:/posts";
  }
}