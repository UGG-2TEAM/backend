package org.example.ugg.domain.diary.converter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.example.ugg.domain.diary.dto.DiaryRequestDTO;
import org.example.ugg.domain.diary.dto.DiaryResponseDTO;
import org.example.ugg.domain.diary.entity.Diary;
import org.example.ugg.domain.user.dto.KakaoDTO;
import org.example.ugg.domain.user.entity.User;
import org.example.ugg.domain.user.entity.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DiaryConverter {
	public static DiaryResponseDTO.diaryResultDTO toDiaryResultDTO(Diary diary) {
		return DiaryResponseDTO.diaryResultDTO.builder()
			.diaryId(diary.getId())
			.content(diary.getContent())
			.build();
	}

	public static DiaryResponseDTO.diaryDetailDTO toDiaryDetailDTO(Diary diary) {
		return DiaryResponseDTO.diaryDetailDTO.builder()
			.diaryId(diary.getId())
			.content(diary.getContent())
			.createdAt(diary.getCreatedAt())
			.updatedAt(diary.getUpdatedAt())
			.build();
	}

	public static DiaryResponseDTO.diaryListDTO toDiaryListDTO(List<Diary> diaries) {


		List<DiaryResponseDTO.diaryEmojiDTO> diaryEmojiDTOList = diaries.stream()
			.map(diary -> DiaryResponseDTO.diaryEmojiDTO.builder()
				.diaryId(diary.getId())
				.emotion(diary.getEmotion())
				.date(Long.valueOf(diary.getCreatedAt().getDayOfMonth()))
				.build()
			)
			.toList();

		return DiaryResponseDTO.diaryListDTO.builder()
			.diaryList(diaryEmojiDTOList)
			.build();
	}

	public static DiaryResponseDTO.frameResultDTO toFrameResultDTO(Diary diary) {
		return DiaryResponseDTO.frameResultDTO.builder()
			.diaryId(diary.getId())
			.build();
	}

	public static DiaryResponseDTO.analysisDTO toAnalysisDTO(Map<String, Long> emotionCounts, double totalDiaries) {
		return DiaryResponseDTO.analysisDTO.builder()
			.angry((double) emotionCounts.getOrDefault("Angry", 0L) * 100 / totalDiaries)
			.disgust((double) emotionCounts.getOrDefault("Disgust", 0L) * 100 / totalDiaries)
			.fear((double) emotionCounts.getOrDefault("Fear", 0L) * 100 / totalDiaries)
			.happy((double) emotionCounts.getOrDefault("Happy", 0L) * 100 / totalDiaries)
			.sad((double) emotionCounts.getOrDefault("Sad", 0L) * 100 / totalDiaries)
			.surprise((double) emotionCounts.getOrDefault("Surprise", 0L) * 100 / totalDiaries)
			.neutral((double) emotionCounts.getOrDefault("Neutral", 0L) * 100 / totalDiaries)
			.build();
	}
}
