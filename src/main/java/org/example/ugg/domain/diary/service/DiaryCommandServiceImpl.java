package org.example.ugg.domain.diary.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.ugg.domain.diary.converter.DiaryConverter;
import org.example.ugg.domain.diary.dto.DiaryRequestDTO;
import org.example.ugg.domain.diary.dto.DiaryResponseDTO;
import org.example.ugg.domain.diary.entity.Diary;
import org.example.ugg.domain.diary.repository.DiaryRepository;

import org.example.ugg.global.config.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryCommandServiceImpl implements DiaryCommandService {
	private final DiaryRepository diaryRepository;



	@Override
	public DiaryResponseDTO.diaryResultDTO writeDiary(DiaryRequestDTO.writeDiaryDTO writeDiaryDTO) {
		Diary diary = diaryRepository.findById(writeDiaryDTO.getDiaryId())
			.orElseThrow(() -> new IllegalArgumentException("다이어리를 찾을 수 없습니다: " + writeDiaryDTO.getDiaryId()));;
		diary.setContent(writeDiaryDTO.getContent());
		diaryRepository.save(diary);
		return DiaryConverter.toDiaryResultDTO(diary);
	}

	@Override
	public DiaryResponseDTO.diaryDetailDTO getDiary(Long diaryId) {
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
	public DiaryResponseDTO.frameResultDTO saveFrameImage(DiaryRequestDTO.saveFrameImageDTO frameImageDTO){
		Diary diary = diaryRepository.findById(frameImageDTO.getDiaryId())
			.orElseThrow(() -> new IllegalArgumentException("다이어리를 찾을 수 없습니다: " + frameImageDTO.getDiaryId()));
		diary.setFrameUrl(frameImageDTO.getFrameImage());
		diaryRepository.save(diary);
		return DiaryConverter.toFrameResultDTO(diary);
	}

	@Override
	public DiaryResponseDTO.emotionResultDTO getframeResult( Long diaryId){
		Diary diary = diaryRepository.findById(diaryId)
			.orElseThrow(() -> new IllegalArgumentException("다이어리를 찾을 수 없습니다: " + diaryId));
		Long frameId;
		switch (diary.getEmotion()) {
			case "angry":
				frameId = 1L;
				break;
			case "disgust":
				frameId = 2L;
				break;
			case "fear":
				frameId = 3L;
				break;
			case "happy":
				frameId = 4L;
				break;
			case "sad":
				frameId = 5L;
				break;
			case "surprise":
				frameId = 6L;
				break;
			case "neutral":
				frameId = 7L;
				break;
			default:
				throw new IllegalArgumentException("지원하지 않는 감정입니다: " + diary.getEmotion());
		}

		return DiaryResponseDTO.emotionResultDTO.builder()
			.frameId(frameId)
			.diaryId(diaryId)
			.imageUrl(diary.getBase64())
			.build();

	}

	@Override
	public DiaryResponseDTO.analysisDTO getAnalysis(CustomUserDetails userDetails, Integer year, Integer month) {
		List<Diary> diaries = diaryRepository.findDiariesByUserIdAndYearAndMonth(userDetails.getUser().getUserId(), year, month);

		// 전체 다이어리 개수
		long totalDiaries = diaries.size();
		if (totalDiaries == 0) {
			return DiaryResponseDTO.analysisDTO.builder()
				.angry(0.0)
				.disgust(0.0)
				.fear(0.0)
				.happy(0.0)
				.sad(0.0)
				.surprise(0.0)
				.neutral(0.0)
				.build();
		}

		// 감정별 개수 계산
		Map<String, Long> emotionCounts = diaries.stream()
			.collect(Collectors.groupingBy(Diary::getEmotion, Collectors.counting()));

		// 감정 비율 계산 및 반환
		return DiaryConverter.toAnalysisDTO(emotionCounts, (double) totalDiaries);
	}

	// @Override
	// public DiaryResponseDTO.frameResultDTO saveFrameImage1(MultipartFile image,
	// 	DiaryRequestDTO.saveFrameImageDTO frameImageDTO) throws
	// 	IOException {
	// 	Diary diary = diaryRepository.findById(frameImageDTO.getDiaryId())
	// 		.orElseThrow(() -> new IllegalArgumentException("다이어리를 찾을 수 없습니다: " + frameImageDTO.getDiaryId()));
	// 	if(!image.isEmpty()) {
	// 		String storedFileName = s3Uploader.upload(image,"images");
	// 		diary.setFrameUrl(storedFileName);
	// 	}
	// 	diary.setFrameUrl(frameImageDTO.getFrameImage());
	// 	diaryRepository.save(diary);
	// 	return DiaryConverter.toFrameResultDTO(diary);
	// }


}
