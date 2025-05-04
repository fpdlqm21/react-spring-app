package com.hansol.hansol.Controller;

import com.hansol.hansol.Domain.User;
import com.hansol.hansol.Dto.AddUserDto;
import com.hansol.hansol.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
// User 생성 요청 처리 controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/users")
//    @RequestBody로 요청 본문 값 매핑(http 요청 본문 그대로 받음) -> ex) name=jun&age=18
//    ResponseEntity는 사용자 요청에 대한 응답 데이터 포함 -> http status나 header, body 데이터 포함
    public ResponseEntity<User> addUser(@RequestBody AddUserDto addUser){
        User savedUser = userService.save(addUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUser);
    }
}
