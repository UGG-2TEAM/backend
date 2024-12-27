package org.example.ugg.domain.letter.service;

import java.util.List;

import org.example.ugg.domain.diary.entity.Diary;
import org.example.ugg.domain.diary.repository.DiaryRepository;
import org.example.ugg.domain.letter.converter.LetterConverter;
import org.example.ugg.domain.letter.dto.LetterResponseDTO;
import org.example.ugg.global.config.security.CustomUserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LetterServiceImpl  implements LetterService {
	private final DiaryRepository diaryRepository;
	@Override
	public LetterResponseDTO.letterResultDTO getLetter(Long diaryId) {
		Diary diary = diaryRepository.findById(diaryId)
			.orElseThrow(() -> new IllegalArgumentException("다이어리를 찾을 수 없습니다: " + diaryId));

		Long emotionId;
		switch (diary.getEmotion()) {
			case "Angry":
				emotionId = 1L;
				break;
			case "Disgust":
				emotionId = 2L;
				break;
			case "Fear":
				emotionId = 3L;
				break;
			case "Happy":
				emotionId = 4L;
				break;
			case "Sad":
				emotionId = 5L;
				break;
			case "Surprise":
				emotionId = 6L;
				break;
			case "Neutral":
				emotionId = 7L;
				break;
			default:
				throw new IllegalArgumentException("지원하지 않는 감정입니다: " + diary.getEmotion());
		}
		return LetterResponseDTO.letterResultDTO.builder()
			.diaryId(diaryId)
			.emotion(emotionId)
			.letterId(diary.getLetter().getId())
			.content(diary.getLetter().getContent())
			.build();

	}
	@Override
	public LetterResponseDTO.letterListDTO getLetterList(CustomUserDetails userDetails){
		List<Diary> diaryList = diaryRepository.findByUserId(userDetails.getUser().getUserId());
		return LetterConverter.letterListDTO(diaryList);
	}
}
