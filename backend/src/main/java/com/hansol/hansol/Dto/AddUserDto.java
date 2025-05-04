package com.hansol.hansol.Dto;

import com.hansol.hansol.Domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddUserDto {

    private String email;
    private String password;
    private String name;
    private String tel;
    private String address;

    public User toEntity(PasswordEncoder passwordEncoder){
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .tel(tel)
                .address(address)
                .build();
    }
}
