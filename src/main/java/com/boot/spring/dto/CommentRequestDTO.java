package com.boot.spring.dto;

import com.boot.spring.entity.Comment;
import com.boot.spring.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {

  private Long postId;
  private String nickname;
  private String comment;

  // DTO → Entity
  public Comment toEntity(Post post) {
    return Comment.builder().post(post).nickname(nickname).comment(comment).build();
  }
}