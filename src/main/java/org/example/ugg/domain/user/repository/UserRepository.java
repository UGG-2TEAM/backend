package org.example.ugg.domain.user.repository;

import java.util.Optional;

import org.example.ugg.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByAccount(String account);

	boolean existsByAccount(String email);

}