package com.sparta.springcore.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String content;
    private String ImgUrl;
    private Long likecount;
}
