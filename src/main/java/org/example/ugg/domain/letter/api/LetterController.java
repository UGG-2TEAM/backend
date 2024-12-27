package org.example.ugg.domain.letter.api;

import org.example.ugg.domain.diary.dto.DiaryResponseDTO;
import org.example.ugg.domain.letter.dto.LetterResponseDTO;
import org.example.ugg.domain.letter.service.LetterService;
import org.example.ugg.domain.user.dto.UserResponseDTO;
import org.example.ugg.global.config.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Tag(name = "3. [편지로직]", description = "편지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/letters")
public class LetterController {
	private final LetterService letterService;

	@Operation(summary = "편지 답장 조회", description = "편지 답장 조회")
	@GetMapping("/")
	public ResponseEntity<LetterResponseDTO.letterResultDTO> getLetter(@RequestParam("diaryId") Long diaryId) {
		LetterResponseDTO.letterResultDTO result = letterService.getLetter(diaryId);
		return ResponseEntity.ok(result);
	}

	@Operation(summary = "편지 답장 전체 조회", description = "편지 답장 전체 조회")
	@GetMapping("/all")
	public ResponseEntity<LetterResponseDTO.letterListDTO> getAllLetter(@AuthenticationPrincipal CustomUserDetails userDetails) {
		LetterResponseDTO.letterListDTO result = letterService.getLetterList(userDetails);
		return ResponseEntity.ok(result);
	}
}
