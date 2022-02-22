package com.sparta.springcore.controller;

import com.sparta.springcore.dto.SignupRequestDto;
import com.sparta.springcore.model.User;
import com.sparta.springcore.repository.UserRepository;
import com.sparta.springcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // 회원 로그인 페이지
    @GetMapping("/api/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/api/register")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
//    @PostMapping("/api/register")
//    public String registerUser(@RequestBody SignupRequestDto requestDto) {
//        System.out.println("aaaaaaaa");
//        userService.registerUser(requestDto);
//        return "redirect:/user/login";

    @PostMapping("/api/register")
    public String registerUser(@Valid @ModelAttribute("requestDto") SignupRequestDto requestDto, BindingResult bindingResult) {

        //회원  ID 중복 확인
        Optional<User> found1 = userRepository.findByUserId(requestDto.getUserId());
        if (found1.isPresent()) {
            FieldError fieldError = new FieldError("requestDto", "userId", "이미 존재하는 ID입니다.");
            bindingResult.addError(fieldError);
        }

        if (!requestDto.getPassword().equals(requestDto.getCheckpw())) {
            FieldError fieldError = new FieldError("requestDto", "checkpw", "암호가 일치하지 않습니다.");
            bindingResult.addError(fieldError);
        }

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        userService.registerUser(requestDto);
        return "redirect:/api/login";
    }
}

