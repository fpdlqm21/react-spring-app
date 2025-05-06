package com.hansol.hansol.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class AddUserDto {

    private String email;
    private String password;
    private String name;
    private String tel;
    private String address;
}
