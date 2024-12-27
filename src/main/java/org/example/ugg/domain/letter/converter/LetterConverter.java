package org.example.ugg.domain.letter.converter;

import java.util.List;

import org.example.ugg.domain.diary.entity.Diary;
import org.example.ugg.domain.letter.dto.LetterResponseDTO;

public class LetterConverter {
	public static LetterResponseDTO.letterListDTO letterListDTO(List<Diary> diaryList){
		List<LetterResponseDTO.letterDTO> letterList = diaryList.stream().map(diary -> {
			Long emotionId;
			switch (diary.getEmotion()) {
				case "angry":
					emotionId = 1L;
					break;
				case "disgust":
					emotionId = 2L;
					break;
				case "fear":
					emotionId = 3L;
					break;
				case "happy":
					emotionId = 4L;
					break;
				case "sad":
					emotionId = 5L;
					break;
				case "surprise":
					emotionId = 6L;
					break;
				case "neutral":
					emotionId = 7L;
					break;
				default:
					throw new IllegalArgumentException("지원하지 않는 감정입니다: " + diary.getEmotion());
			}
			return LetterResponseDTO.letterDTO.builder()
				.letterId(diary.getLetter().getId())
				.emotion(emotionId)
				.createdAt(diary.getLetter().getCreatedAt())
				.build();
		}).toList();
		return LetterResponseDTO.letterListDTO.builder()
			.letterList(letterList)
			.build();
	}
}
