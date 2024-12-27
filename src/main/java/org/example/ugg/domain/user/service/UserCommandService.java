package org.example.ugg.domain.user.service;

import org.example.ugg.domain.user.dto.UserResponseDTO;

import jakarta.servlet.http.HttpServletResponse;

public interface UserCommandService {

	UserResponseDTO.loginResultDTO oAuthKakaoLogin(String accessCode, HttpServletResponse httpServletResponse);
}
