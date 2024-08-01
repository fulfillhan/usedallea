package com.application.usedallea.auth.controller;

import com.application.usedallea.old.member.dto.MemberDTO;
import com.application.usedallea.user.dto.UserRegisterDto;
import com.application.usedallea.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @GetMapping("/login-form")
    public String login() {
        return "member/login";
    }
    
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserRegisterDto userDto, HttpSession session) {
        boolean validateLogin = userService.loginUser(userDto);
        if (validateLogin) {
            //로그인 성공시 세션 설정
            session.setAttribute("userId", userDto.getUserId());
        }
        return ResponseEntity.ok(validateLogin);
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();// 세션 무효화
        return "redirect:/usedallea/main";
    }

}
