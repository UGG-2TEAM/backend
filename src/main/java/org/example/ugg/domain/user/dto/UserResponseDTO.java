package org.example.ugg.domain.user.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDTO {
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class loginResultDTO {
		Long memberId;
		String account;
		String accessToken;
		LocalDateTime createdAt;
	}
}
