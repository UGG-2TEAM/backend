package org.example.ugg.domain.diary.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	@Override
	public DiaryResponseDTO.diaryDetailDTO getDiary(CustomUserDetails userDetails, Long diaryId) {
		Diary diary = diaryRepository.findById(diaryId)
			.orElseThrow(() -> new IllegalArgumentException("다이어리를 찾을 수 없습니다: " + diaryId));
		return DiaryConverter.toDiaryDetailDTO(diary);
	}

	@Override
	public DiaryResponseDTO.diaryListDTO getDiaryList(CustomUserDetails userDetails, Integer year, Integer month) {
		List<Diary> diaryList = diaryRepository.findDiariesByUserIdAndYearAndMonth(userDetails.getUser().getUserId(),year, month);
		return DiaryConverter.toDiaryListDTO(diaryList);
	}

	@Override
	public DiaryResponseDTO.frameResultDTO saveFrameImage(CustomUserDetails userDetails, DiaryRequestDTO.saveFrameImageDTO frameImageDTO){
		Diary diary = diaryRepository.findById(frameImageDTO.getDiaryId())
			.orElseThrow(() -> new IllegalArgumentException("다이어리를 찾을 수 없습니다: " + frameImageDTO.getDiaryId()));
		diary.setFrameUrl(frameImageDTO.getFrameImage());
		diaryRepository.save(diary);
		return DiaryConverter.toFrameResultDTO(diary);
	}

	@Override
	public DiaryResponseDTO.emotionResultDTO getframeResult(CustomUserDetails userDetails, Long diaryId){
		Diary diary = diaryRepository.findById(diaryId)
			.orElseThrow(() -> new IllegalArgumentException("다이어리를 찾을 수 없습니다: " + diaryId));
		Long frameId;
		switch (diary.getEmotion()) {
			case "Angry":
				frameId = 1L;
				break;
			case "Disgust":
				frameId = 2L;
				break;
			case "Fear":
				frameId = 3L;
				break;
			case "Happy":
				frameId = 4L;
				break;
			case "Sad":
				frameId = 5L;
				break;
			case "Surprise":
				frameId = 6L;
				break;
			case "Neutral":
				frameId = 7L;
				break;
			default:
				throw new IllegalArgumentException("지원하지 않는 감정입니다: " + diary.getEmotion());
		}

		return DiaryResponseDTO.emotionResultDTO.builder()
			.frameId(frameId)
			.diaryId(diaryId)
			.imageUrl(diary.getImageUrl())
			.build();

	}

	@Override
	public DiaryResponseDTO.analysisDTO getAnalysis(CustomUserDetails userDetails, Integer year, Integer month){
		List<Diary> diaries = diaryRepository.findDiariesByUserIdAndYearAndMonth(userDetails.getUser().getUserId(),year, month);

		// 전체 다이어리 개수
		long totalDiaries = diaries.size();
		if (totalDiaries == 0) {
			return DiaryResponseDTO.analysisDTO.builder()
				.angry(0L)
				.disgust(0L)
				.fear(0L)
				.happy(0L)
				.sad(0L)
				.surprise(0L)
				.neutral(0L)
				.build();
		}

		// 감정별 개수 계산
		Map<String, Long> emotionCounts = diaries.stream()
			.collect(Collectors.groupingBy(Diary::getEmotion, Collectors.counting()));
		return DiaryConverter.toAnalysisDTO(emotionCounts, totalDiaries);
	}


}
