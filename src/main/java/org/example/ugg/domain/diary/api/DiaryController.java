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
@RequestMapping("/api/v1/diary")
public class DiaryController {
	private final DiaryCommandService diaryCommandService;

	@Operation(summary = "일기 작성 API", description = "일기 작성")
	@PostMapping("/")
	public ResponseEntity<DiaryResponseDTO.diaryResultDTO> writeDiary( @RequestBody
		DiaryRequestDTO.writeDiaryDTO writeDiaryDTO) {
		DiaryResponseDTO.diaryResultDTO result = diaryCommandService.writeDiary( writeDiaryDTO);
		return ResponseEntity.ok(result);
	}

	@Operation(summary = "일기 상세정보 API", description = "일기 상세정보 조회")
	@GetMapping("/")
	public ResponseEntity<DiaryResponseDTO.diaryDetailDTO> getDiary( @RequestParam("diaryId") Long diaryId) {
		DiaryResponseDTO.diaryDetailDTO result = diaryCommandService.getDiary(diaryId);
		return ResponseEntity.ok(result);
	}

	@Operation(summary="캘린더 정보 조회 API", description="캘린더 정보 조회")
	@GetMapping("/list")
	public ResponseEntity<DiaryResponseDTO.diaryListDTO> getDiaryList(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam("year") int year, @RequestParam("month") int month) {
		DiaryResponseDTO.diaryListDTO result = diaryCommandService.getDiaryList(userDetails, year, month);
		return ResponseEntity.ok(result);
	}

	@Operation(summary="프레임 이미지 저장 API", description="프레임 이미지 저장")
	@PostMapping("/frame")
	public ResponseEntity<DiaryResponseDTO.frameResultDTO> saveFrameImage(@RequestBody DiaryRequestDTO.saveFrameImageDTO saveFrameImageDTO) {
		DiaryResponseDTO.frameResultDTO result = diaryCommandService.saveFrameImage( saveFrameImageDTO);
		return ResponseEntity.ok(result);
	}

	@Operation(summary="감정 분석 기반 프레임 값 조회 API", description = "감정 분석 기반 프레임 값 조회")
	@GetMapping("/result")
	public ResponseEntity<DiaryResponseDTO.emotionResultDTO> getFrameImage( @RequestParam("diaryId") Long diaryId) {
		DiaryResponseDTO.emotionResultDTO result = diaryCommandService.getframeResult(diaryId);
		return ResponseEntity.ok(result);
	}

	@Operation(summary="감정 분석 결과", description="감정 분석 결과")
	@GetMapping("/analysis")
	public ResponseEntity<DiaryResponseDTO.analysisDTO> getAnalysis(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam("year") int year, @RequestParam("month") int month) {
		DiaryResponseDTO.analysisDTO result = diaryCommandService.getAnalysis(userDetails, year, month);
		return ResponseEntity.ok(result);
	}



}
