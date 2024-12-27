package org.example.ugg.domain.user.service;

import org.example.ugg.domain.user.UserConverter.UserConverter;
import org.example.ugg.domain.user.dto.KakaoDTO;
import org.example.ugg.domain.user.dto.UserResponseDTO;
import org.example.ugg.domain.user.entity.User;
import org.example.ugg.domain.user.repository.UserRepository;
import org.example.ugg.global.config.security.JwtUtil;
import org.example.ugg.global.config.security.KakaoUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final KakaoUtil kakaoUtil;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserResponseDTO.loginResultDTO oAuthKakaoLogin(String accessCode, HttpServletResponse httpServletResponse) {
		KakaoDTO.OAuthToken oAuthToken = kakaoUtil.requestToken(accessCode);
		KakaoDTO.KakaoProfile kakaoProfile = kakaoUtil.requestProfile(oAuthToken);
		String email = kakaoProfile.getKakao_account().getEmail();
		User user = userRepository.findByAccount(email)
			.orElseGet(() -> createNewUser(kakaoProfile));
		String token = jwtUtil.createAccessToken(UserConverter.toCustomUserInfoDto(user));
		return UserConverter.loginResult(user, token);

	}

	private User createNewUser(KakaoDTO.KakaoProfile kakaoProfile) {
		User newUser = UserConverter.toUser(kakaoProfile, passwordEncoder);
		return userRepository.save(newUser);
	}
}
