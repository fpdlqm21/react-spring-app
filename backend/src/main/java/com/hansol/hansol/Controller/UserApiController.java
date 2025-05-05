package com.hansol.hansol.Controller;

import com.hansol.hansol.Dto.AddUserDto;
import com.hansol.hansol.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
// User 생성 요청 처리 controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserDto request){
        System.out.println(userService.save(request)); // 회원 가입 메소드 호출
        return "redirect:/login"; // 회원 가입 후 로그인 페이지 리다이렉트
    }
}
