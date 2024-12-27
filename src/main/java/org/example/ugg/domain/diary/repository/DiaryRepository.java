package org.example.ugg.domain.diary.repository;

import java.util.Optional;

import org.example.ugg.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
	Optional<Diary> findById(Long id);
}
