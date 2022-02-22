package com.sparta.springcore.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Setter
@Getter
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp="^[a-zA-Z0-9]{3,12}$", message="아이디를 3~12자로 입력해주세요.(특수문자x)")
    private String userId;

    @Pattern(regexp="^[a-zA-Z0-9]{4,12}$", message="비밀번호를 4~12자로 입력해주세요.")
    private String password;

    @NotBlank(message = "확인 비밀번호를 입력해주세요")
    private String checkpw;

    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;
}
