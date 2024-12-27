package org.example.ugg.domain.user.api;

import org.example.ugg.domain.user.dto.UserResponseDTO;
import org.example.ugg.domain.user.service.UserCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Tag(name = "3. [회원로직]", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserCommandService userCommandService;

	//카카오 소셜 로그인 API
	@Operation(summary = "카카오 소셜 로그인", description = "카카오 소셜 로그인")
	@GetMapping("/auth/login/kakao")
	public ResponseEntity<UserResponseDTO.loginResultDTO> kakaoLogin(@RequestParam("code") String accessCode,
		HttpServletResponse httpServletResponse) {
		UserResponseDTO.loginResultDTO result = userCommandService.oAuthKakaoLogin(accessCode, httpServletResponse);
		return ResponseEntity.ok(result);
	}
}
