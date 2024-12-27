package org.example.ugg.domain.user.entity;

import java.util.ArrayList;
import java.util.List;

import org.example.ugg.domain.diary.entity.Diary;
import org.example.ugg.domain.letter.entity.Letter;
import org.example.ugg.domain.user.common.FullTimeAuditEntity;
import org.hibernate.annotations.Comment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

public class User extends FullTimeAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("아이디")
	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
	private String account;

	@Comment("비밀번호")
	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Diary> diaries = new ArrayList<>();

	public void encodedPassword(String password) {
		this.password = password;
	}




}