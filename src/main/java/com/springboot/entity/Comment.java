package com.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor  // 기본 생성자 자동 생성 (엔티티를 생성할 때 필요함)
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자 자동 생성 (@Builder를 사용할 때 필요함)
@Builder            // 객체를 빌더 패턴으로 생성할 수 있음
public class Comment {

  @Id // 기본 키 지정
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne  // Comment 엔티티와 Post 엔티티를 다대일 관계로 설정
  @JoinColumn(name = "post_id") // 외래키
  private Post post;

  private String nickname;
  private String comment;

  // 생성 시간은 한 번 저장된 후에는 수정되지 않도록 설정
  @Column(updatable = false)
  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  // 데이터가 처음 저장되기 전에 실행됨
  @PrePersist
  public void onPrePersist() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  // 기존 데이터가 수정되기 전에 실행됨
  @PreUpdate
  public void onPreUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}