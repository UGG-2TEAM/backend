package org.example.ugg.domain.diary.service;

import org.example.ugg.domain.diary.converter.DiaryConverter;
import org.example.ugg.domain.diary.dto.DiaryRequestDTO;
import org.example.ugg.domain.diary.dto.DiaryResponseDTO;
import org.example.ugg.domain.diary.entity.Diary;
import org.example.ugg.domain.diary.repository.DiaryRepository;
import org.example.ugg.domain.user.dto.UserResponseDTO;
import org.example.ugg.domain.user.repository.UserRepository;
import org.example.ugg.domain.user.service.UserCommandService;
import org.example.ugg.global.config.security.CustomUserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryCommandServiceImpl implements DiaryCommandService {
	private final DiaryRepository diaryRepository;

	@Override
	public DiaryResponseDTO.diaryResultDTO writeDiary(CustomUserDetails userDetails, DiaryRequestDTO.writeDiaryDTO writeDiaryDTO) {
		Diary diary = diaryRepository.findById(writeDiaryDTO.getDiaryId())
			.orElseThrow(() -> new IllegalArgumentException("다이어리를 찾을 수 없습니다: " + writeDiaryDTO.getDiaryId()));;
		diary.setContent(writeDiaryDTO.getContent());
		diaryRepository.save(diary);
		return DiaryConverter.toDiaryResultDTO(diary);
	}

}
