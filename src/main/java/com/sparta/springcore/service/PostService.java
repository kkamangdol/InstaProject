package com.sparta.springcore.service;

import com.sparta.springcore.dto.PostRequestDto;
import com.sparta.springcore.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.sparta.springcore.model.Likes;
import com.sparta.springcore.model.Post;
import com.sparta.springcore.model.User;
import com.sparta.springcore.repository.LikeRepository;
import com.sparta.springcore.repository.PostRepository;
import com.sparta.springcore.security.UserDetailsImpl;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public List<PostResponseDto> getListOfPost() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> responseDtoList = new ArrayList<>();

        for (Post post : posts) {
            Long likeCount = likeRepository.countByPost(post);
            responseDtoList.add(new PostResponseDto(post, likeCount));
        }
        return responseDtoList;
    }

    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        Long likeCount = likeRepository.countByPost(post);
        return new PostResponseDto(post, likeCount);
    }

    public PostResponseDto writePost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);
        return new PostResponseDto(postRepository.save(post), 0L);

    }

    @Transactional
    public Long updatePost(Long postId, PostRequestDto requestDto) {
        Post post = findPost(postId);

        checkUser(post.getUser().getUserId());

        post.update(requestDto);
        return postId;
    }

    public Long deletePost(Long postId) {
        Post post = findPost(postId);
        checkUser(post.getUser().getUserId());
        postRepository.deleteById(postId);
        return postId;
    }

    public Long changeLike(Long postId, User user) {
        Post post = findPost(postId);

        Optional<Likes> like = likeRepository.findByPostAndUser(post, user);
        if (like.isPresent()) {
            likeRepository.delete(like.get());
        } else {
            likeRepository.save(new Likes(post, user));
        }
        return postId;
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("게시글이 없습니다")
        );
    }

    private void checkUser(String userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        if (!(userId.equals(userDetails.getUsername())))
            throw new AccessDeniedException("게시글 작성자만 접근할 수 있습니다");
    }
}
