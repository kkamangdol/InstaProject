package com.sparta.springcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.springcore.model.Likes;
import com.sparta.springcore.model.Post;
import com.sparta.springcore.model.User;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByPostAndUser(Post post, User user);
    Long countByPost(Post post);
}
