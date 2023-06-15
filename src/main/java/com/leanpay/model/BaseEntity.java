package com.leanpay.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private Long id;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

}
