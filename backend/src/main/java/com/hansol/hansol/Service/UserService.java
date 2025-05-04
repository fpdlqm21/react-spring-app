package com.hansol.hansol.Service;

import com.hansol.hansol.Domain.User;
import com.hansol.hansol.Dto.AddUserDto;
import com.hansol.hansol.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    //@RequiredArgsConstructor가 있어서 @Autowired 없이 final필드 자동 생성자 주입
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // password 암호화



//    user dto -> Entity화 -> DB에 저장
    public User save(AddUserDto addUser){

        if (userRepository.existsByEmail(addUser.getEmail())) throw new IllegalArgumentException("이미 가입된 이메일입니다.");

        return userRepository.save(addUser.toEntity(passwordEncoder)); // ID(PK)가 채워진 User객체 반환.
    }
}
