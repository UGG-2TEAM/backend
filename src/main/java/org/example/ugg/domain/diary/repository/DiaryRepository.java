package org.example.ugg.domain.diary.repository;

import java.util.List;
import java.util.Optional;

import org.example.ugg.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
	Optional<Diary> findById(Long id);

	@Query("SELECT d FROM Diary d WHERE d.user.id = :userId AND FUNCTION('YEAR', d.createdAt) = :year AND FUNCTION('MONTH', d.createdAt) = :month")
	List<Diary> findDiariesByUserIdAndYearAndMonth(@Param("userId") Long userId,
		@Param("year") int year,
		@Param("month") int month);
}
