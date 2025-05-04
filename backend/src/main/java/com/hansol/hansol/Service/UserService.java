package com.hansol.hansol.Service;

import com.hansol.hansol.Domain.User;
import com.hansol.hansol.Dto.AddUserDto;
import com.hansol.hansol.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// 회원가입 서비스 구현
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserDto dto){
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword())) // password 암호화
                .build()).getId();
    }
}
