package com.sparta.springcore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.sparta.springcore.dto.PostRequestDto;
import com.sparta.springcore.dto.PostResponseDto;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 조회
    @GetMapping("/api/post")
    public List<PostResponseDto> getListOfPosts() {
        return postService.getListOfPost();
    }

    @GetMapping("/api/post/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }
    // 게시글 작성
    @PostMapping("/api/post")
    public PostResponseDto writePost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.writePost(requestDto, userDetails.getUser());
    }

    // 게시글 수정
    @PutMapping("/api/post/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(postId, requestDto);
    }
    // 좋아요
    @PutMapping("/api/post/{postId}/like")
    public Long changeLike(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.changeLike(postId, userDetails.getUser());
    }
//    // 좋아요 취소
//    @DeleteMapping("/api/post/{postId}/like")



    // 게시글 삭제
    @DeleteMapping("/api/post/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }

}
