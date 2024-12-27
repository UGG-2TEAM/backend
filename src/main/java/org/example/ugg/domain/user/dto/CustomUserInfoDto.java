package org.example.ugg.domain.user.dto;

import org.example.ugg.domain.user.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserInfoDto {
	private Long userId;
	private String account;
	private UserRole role;
	private String password;
}

