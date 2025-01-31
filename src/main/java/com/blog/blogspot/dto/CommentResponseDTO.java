package com.blog.blogspot.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String author;
    private String firstName;
    private String lastName;
    private long upvoteCount;
    private long downvoteCount;

    public CommentResponseDTO(Long id, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String username, String firstName, String lastName, long upvoteCount, long downvoteCount) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.upvoteCount = upvoteCount;
        this.downvoteCount = downvoteCount;
    }
}
