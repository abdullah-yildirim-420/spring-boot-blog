package com.blog.blogspot.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String author;
    private String firstName;
    private String lastName;
    private long upvoteCount;
    private long downvoteCount;
    private long commentCount;

    public PostResponseDTO(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String username, String firstName, String lastName, long upvoteCount, long downvoteCount, long commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.upvoteCount = upvoteCount;
        this.downvoteCount = downvoteCount;
        this.commentCount = commentCount;
    }
}