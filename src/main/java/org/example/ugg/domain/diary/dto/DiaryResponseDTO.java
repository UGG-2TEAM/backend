package org.example.ugg.domain.diary.dto;

import java.time.LocalDateTime;
import java.util.List;

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

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class diaryDetailDTO {
		Long diaryId;
		String content;
		String emotion;
		String image_url;
		LocalDateTime createdAt;
		LocalDateTime updatedAt;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class diaryListDTO {
		List<diaryEmojiDTO> diaryList;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class diaryEmojiDTO {
		Long diaryId;
		String emotion;
		Long date;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class frameResultDTO {
		Long diaryId;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class emotionResultDTO {
		Long diaryId;
		Long frameId;
		String imageUrl;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class analysisDTO {
		Long angry;
		Long disgust;
		Long fear;
		Long happy;
		Long sad;
		Long surprise;
		Long neutral;
	}




}
