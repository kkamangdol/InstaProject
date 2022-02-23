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

    @GetMapping("/api/post")
    public List<PostResponseDto> getListOfPosts() {
        return postService.getListOfPost();
    }

    @GetMapping("/api/post/:postId")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PostMapping("/api/post")
    public PostResponseDto writePost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.writePost(requestDto, userDetails.getUser());
    }

    @PutMapping("/api/post/:postId")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    @PutMapping("/api/post/{id}/like")
    public Long changeLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.changeLike(id, userDetails.getUser());
    }

    @DeleteMapping("/api/post/:postId")
    public Long deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

}
