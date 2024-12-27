package org.example.ugg.domain.letter.service;

import org.example.ugg.domain.letter.dto.LetterResponseDTO;
import org.example.ugg.global.config.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;

public interface LetterService {
	LetterResponseDTO.letterResultDTO getLetter(Long letterId);


	LetterResponseDTO.letterListDTO getLetterList(CustomUserDetails userDetails);

	LetterResponseDTO.letterResultDTO getLetterById(Long letterId);
}
