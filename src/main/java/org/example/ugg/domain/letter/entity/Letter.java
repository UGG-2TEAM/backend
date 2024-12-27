package org.example.ugg.domain.letter.entity;

import org.example.ugg.domain.diary.entity.Diary;
import org.example.ugg.domain.user.common.FullTimeAuditEntity;
import org.example.ugg.domain.user.entity.User;
import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
public class Letter extends FullTimeAuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("내용")
	@Column(columnDefinition = "TEXT")
	private String content;

	@OneToOne
	@JoinColumn(name = "diary_id", nullable = false)
	private Diary diary;








}
