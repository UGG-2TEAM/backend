package org.example.ugg.domain.diary.api;

import org.example.ugg.domain.diary.dto.DiaryRequestDTO;
import org.example.ugg.domain.diary.dto.DiaryResponseDTO;
import org.example.ugg.domain.diary.service.DiaryCommandService;
import org.example.ugg.domain.user.dto.UserResponseDTO;
import org.example.ugg.global.config.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Tag(name = "3. [일기로직]", description = "일기 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diaries")
public class DiaryController {
	private final DiaryCommandService diaryCommandService;

	@Operation(summary = "일기 작성 API", description = "일기 작성")
	@PostMapping("/")
	public ResponseEntity<DiaryResponseDTO.diaryResultDTO> writeDiary(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody
		DiaryRequestDTO.writeDiaryDTO writeDiaryDTO) {
		DiaryResponseDTO.diaryResultDTO result = diaryCommandService.writeDiary(userDetails, writeDiaryDTO);
		return ResponseEntity.ok(result);
	}

}
