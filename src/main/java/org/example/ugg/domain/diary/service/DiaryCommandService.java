package org.example.ugg.domain.diary.service;

import org.example.ugg.domain.diary.dto.DiaryRequestDTO;
import org.example.ugg.domain.diary.dto.DiaryResponseDTO;
import org.example.ugg.global.config.security.CustomUserDetails;

public interface DiaryCommandService {
	DiaryResponseDTO.diaryResultDTO writeDiary(CustomUserDetails userDetails, DiaryRequestDTO.writeDiaryDTO writeDiaryDTO);
}
