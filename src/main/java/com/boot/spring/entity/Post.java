package com.boot.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor  // 엔티티를 생성할 때 필요함
@AllArgsConstructor // @Builder를 사용할 때 필요함
@Builder            // 객체를 빌더 패턴으로 생성할 수 있음
public class Post {

  @Id // 기본 키 지정
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String content;

  @Column(updatable = false)
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