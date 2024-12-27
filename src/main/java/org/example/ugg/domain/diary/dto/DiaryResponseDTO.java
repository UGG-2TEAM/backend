package org.example.ugg.domain.diary.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DiaryResponseDTO {
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class diaryResultDTO {
		Long diaryId;
		String content;
	}
}
