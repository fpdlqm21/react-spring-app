package com.hansol.hansol.Service;

import com.hansol.hansol.Domain.User;
import com.hansol.hansol.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// UserDetailsService -> DB에서 해당 사용자를 조회하는 로직 담당
// 로그인 서비스 구현
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

//    email로 사용자의 정보 가져오는 메소드
    @Override
    public User loadUserByUsername(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }
}
