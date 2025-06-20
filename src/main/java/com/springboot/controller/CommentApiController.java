package com.springboot.controller;

import com.springboot.dto.CommentRequestDTO;
import com.springboot.dto.CommentResponseDTO;
import com.springboot.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments/api")
public class CommentApiController {

  private final CommentService commentService;

  // 생성자가 하나뿐인 경우 @Autowired 생략 가능 (Spring 4.3 이상)
  public CommentApiController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping("/{postId}")
  public ResponseEntity<List<CommentResponseDTO>> getAllComments(@PathVariable Long postId) {
    List<CommentResponseDTO> commentResponseDTOList = commentService.getAllComments(postId);
    return ResponseEntity.status(HttpStatus.OK).body(commentResponseDTOList);
  }

  @PostMapping("/new")
  public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
    System.out.println("CommentRequestDTO: " + commentRequestDTO);
    CommentResponseDTO commentResponseDTO = commentService.createComment(commentRequestDTO);
    return ResponseEntity.status(HttpStatus.OK).body(commentResponseDTO);
  }
}