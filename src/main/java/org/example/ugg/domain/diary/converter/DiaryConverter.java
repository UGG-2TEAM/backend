package org.example.ugg.domain.diary.converter;

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

}
