package org.example.ugg.domain.diary.service;

import java.io.IOException;

import org.example.ugg.domain.diary.dto.DiaryRequestDTO;
import org.example.ugg.domain.diary.dto.DiaryResponseDTO;
import org.example.ugg.global.config.security.CustomUserDetails;
import org.springframework.web.multipart.MultipartFile;

public interface DiaryCommandService {
	DiaryResponseDTO.diaryResultDTO writeDiary(DiaryRequestDTO.writeDiaryDTO writeDiaryDTO);
	DiaryResponseDTO.diaryDetailDTO getDiary(Long diaryId);

	DiaryResponseDTO.diaryListDTO getDiaryList(CustomUserDetails userDetails, Integer year, Integer month);

	DiaryResponseDTO.frameResultDTO saveFrameImage(DiaryRequestDTO.saveFrameImageDTO frameImageDTO);
	DiaryResponseDTO.emotionResultDTO getframeResult(Long diaryId);

	DiaryResponseDTO.analysisDTO getAnalysis(CustomUserDetails userDetails, Integer year, Integer month);


}
