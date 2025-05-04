package com.hansol.hansol.Repository;

import com.hansol.hansol.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email); // 이메일이 DB에 존재하는지 확인 -> true, false반환
//    ex) userRepository.existsByEmail("fpdlqm21@naver.com")
}
