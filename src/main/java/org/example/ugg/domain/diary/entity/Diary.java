package org.example.ugg.domain.diary.entity;

import org.example.ugg.domain.user.common.FullTimeAuditEntity;
import org.example.ugg.domain.user.entity.User;
import org.example.ugg.domain.user.entity.UserRole;
import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Diary extends FullTimeAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("이미지_URL")
	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
	private String imageUrl;

	@Comment("감정")
	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String emotion;

	@Comment("내용")
	@Column(columnDefinition = "TEXT")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}





