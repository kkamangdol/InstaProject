package com.sparta.springcore.dto;

import lombok.Getter;
import lombok.Setter;
import com.sparta.springcore.model.Post;

import java.time.LocalDateTime;

@Setter
@Getter
public class PostResponseDto {
    private String content;
    private String imgUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String nickname;
    private Long likeCount;

    public PostResponseDto(Post post, Long likeCount) {
        this.content = post.getContent();
        this.imgUrl = post.getImgUrl();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.nickname = post.getUser().getNickname();
        this.likeCount = likeCount;
    }
}