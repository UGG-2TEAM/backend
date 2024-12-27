package org.example.ugg.domain.user.UserConverter;

import org.example.ugg.domain.user.dto.CustomUserInfoDto;
import org.example.ugg.domain.user.dto.KakaoDTO;
import org.example.ugg.domain.user.dto.UserResponseDTO;
import org.example.ugg.domain.user.entity.User;
import org.example.ugg.domain.user.entity.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserConverter {

	public static User toUser(KakaoDTO.KakaoProfile kakaoProfile, PasswordEncoder passwordEncoder) {
		return User.builder()
			.account(kakaoProfile.getKakao_account().getEmail())
			.password(passwordEncoder.encode("kakao"))
			.role(UserRole.USER)
			.build();
	}

	public static UserResponseDTO.loginResultDTO loginResult(User user, String accessToken) {
		return UserResponseDTO.loginResultDTO.builder()
			.memberId(user.getId())
			.account(user.getAccount())
			.createdAt(user.getCreatedAt())
			.accessToken(accessToken)
			.build();
	}

	public static CustomUserInfoDto toCustomUserInfoDto(User user) {
		return CustomUserInfoDto.builder()
			.userId(user.getId())
			.account(user.getAccount())
			.role(user.getRole())
			.build();
	}
}
