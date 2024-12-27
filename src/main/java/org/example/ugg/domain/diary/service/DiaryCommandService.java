package org.example.ugg.domain.diary.service;

import org.example.ugg.domain.diary.dto.DiaryRequestDTO;
import org.example.ugg.domain.diary.dto.DiaryResponseDTO;
import org.example.ugg.global.config.security.CustomUserDetails;

public interface DiaryCommandService {
	DiaryResponseDTO.diaryResultDTO writeDiary(CustomUserDetails userDetails, DiaryRequestDTO.writeDiaryDTO writeDiaryDTO);
	DiaryResponseDTO.diaryDetailDTO getDiary(CustomUserDetails userDetails, Long diaryId);

	DiaryResponseDTO.diaryListDTO getDiaryList(CustomUserDetails userDetails, Integer year, Integer month);

	DiaryResponseDTO.frameResultDTO saveFrameImage(CustomUserDetails userDetails, DiaryRequestDTO.saveFrameImageDTO frameImageDTO);
	DiaryResponseDTO.emotionResultDTO getframeResult(CustomUserDetails userDetails, Long diaryId);

	DiaryResponseDTO.analysisDTO getAnalysis(CustomUserDetails userDetails, Integer year, Integer month);
}
