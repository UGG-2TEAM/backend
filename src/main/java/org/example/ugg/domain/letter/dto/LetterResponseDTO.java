package org.example.ugg.domain.letter.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LetterResponseDTO {
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class letterResultDTO {
		Long diaryId;
		Long letterId;
		Long emotion;
		String content;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class letterListDTO {
		List<letterDTO> letterList;

	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class letterDTO {
		Long letterId;
		Long emotion;
		LocalDateTime createdAt;

	}
}
