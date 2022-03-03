package com.sparta.springcore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.sparta.springcore.dto.PostRequestDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column
    private String imgUrl;

    @Column
    private Long likecount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(PostRequestDto requestDto, User user) {
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
        this.likecount = requestDto.getLikecount();
        this.user = user;
    }

    public void update(PostRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
        this.likecount = requestDto.getLikecount();
    }
}
