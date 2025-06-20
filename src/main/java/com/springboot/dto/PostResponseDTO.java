package com.springboot.dto;

import com.springboot.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {

  private Long id;
  private String title;
  private String content;
  private LocalDateTime updatedAt;

  // 뷰에서는 formattedUpdateAt으로 접근하여 날짜를 보기 좋은 형식으로 출력할 수 있다.
  public String getFormattedUpdateAt() {
    return this.updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  // Entity → DTO
  public static PostResponseDTO toDTO(Post post) {
    return new PostResponseDTO(post.getId(), post.getTitle(), post.getContent(), post.getUpdatedAt());
  }
}