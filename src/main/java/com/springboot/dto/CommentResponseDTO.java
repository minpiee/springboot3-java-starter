package com.springboot.dto;

import com.springboot.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {

  private Long id;
  private Long postId;
  private String nickname;
  private String comment;
  private LocalDateTime updatedAt;

  // 뷰에서는 formattedUpdateAt으로 접근하여 날짜를 보기 좋은 형식으로 출력할 수 있다.
  public String getFormattedUpdateAt() {
    return this.updatedAt.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
  }

  // Entity → DTO
  public static CommentResponseDTO toDTO(Comment comment) {
    return new CommentResponseDTO(comment.getId(), comment.getPost().getId(), comment.getNickname(), comment.getComment(), comment.getUpdatedAt());
  }
}