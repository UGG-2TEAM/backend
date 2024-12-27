package org.example.ugg.domain.user.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class FullTimeAuditEntity extends CreatedTimeEntity {
	@LastModifiedDate
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}