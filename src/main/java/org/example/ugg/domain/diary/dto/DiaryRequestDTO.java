package org.example.ugg.domain.diary.dto;

import lombok.Getter;

public class DiaryRequestDTO {

	@Getter
	public static class writeDiaryDTO {
		private Long diaryId;
		private String content;
	}
}
