package com.boot.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor  // JPA에서 엔티티를 생성할 때 필요함
@AllArgsConstructor // @Builder를 사용할 때 필요함
@Builder            // 객체를 빌더 패턴으로 생성할 수 있게 함
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne  // Comment 엔티티와 Post 엔티티를 다대일 관계로 설정
  @JoinColumn(name = "post_id") // 외래키
  private Post post;

  private String nickname;
  private String comment;

  @Column(updatable = false) // UPDATE 쿼리에서 제외됨
  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @PrePersist // 데이터가 처음 저장되기 전에 실행됨
  public void onPrePersist() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  @PreUpdate // 기존 데이터가 수정되기 전에 실행됨
  public void onPreUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}