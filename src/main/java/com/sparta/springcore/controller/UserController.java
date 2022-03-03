package com.sparta.springcore.controller;

import com.sparta.springcore.dto.SignupRequestDto;
import com.sparta.springcore.model.ErrorMessage;
import com.sparta.springcore.repository.UserRepository;
import com.sparta.springcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
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

//    @PostMapping("/api/register")
//    public String registerUser(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
//
//        //회원  ID 중복 확인
//        Optional<User> found1 = userRepository.findByUserId(requestDto.getUserId());
//        if (found1.isPresent()) {
//            FieldError fieldError = new FieldError("requestDto", "userId", "이미 존재하는 ID입니다.");
//            bindingResult.addError(fieldError);
//        }
//        System.out.println(requestDto.getPassword()+ "asdfd" );
//        System.out.println(requestDto.getCheckPw()+ "asdfd" );
//
//        if (!requestDto.getPassword().equals(requestDto.getCheckPw())) {
//            FieldError fieldError = new FieldError("requestDto", "checkPw", "암호가 일치하지 않습니다.");
//            bindingResult.addError(fieldError);
//        }
//
//        if (bindingResult.hasErrors()) {
//            return "signup";
//        }
//
//        userService.registerUser(requestDto);
//        return "redirect:/api/login";

        // 회원 가입 요청 처리
        @PostMapping("/api/register")
        @ResponseBody
        public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {

            if (bindingResult.hasErrors()) {
                List<ErrorMessage> errorMessages = new ArrayList<>();
                bindingResult.getAllErrors().forEach(objectError -> {
                    errorMessages.add(new ErrorMessage(objectError.getDefaultMessage()));
                });
                return ResponseEntity.badRequest().body(errorMessages);
            }

            userService.registerUser(requestDto);
            return ResponseEntity.ok().body("");
    }
}

