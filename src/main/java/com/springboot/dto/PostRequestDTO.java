package com.springboot.dto;

import com.springboot.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDTO {

  private Long id;
  private String title;
  private String content;

  // DTO → Entity
  public Post toEntity() {
    // return new Post(id, title, content, null, null);
    return Post.builder().id(id).title(title).content(content).build();
  }
}