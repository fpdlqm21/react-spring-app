package com.hansol.hansol.Controller;

import com.hansol.hansol.Dto.AddUserDto;
import com.hansol.hansol.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
// User 생성 요청 처리 controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserDto request){
        userService.save(request); // 회원 가입 메소드 호출
        return "redirect:/login"; // 회원 가입 후 로그인 페이지 리다이렉트
    }

//    Logout
    /*
    * Logout 요청 시 로그아웃 담당하는 핸들러 SecurityContextHolder의 logout()메소드 사용
    * */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
