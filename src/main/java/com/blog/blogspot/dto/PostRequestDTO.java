package com.blog.blogspot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(max = 128, message = "Title cannot be longer than 128 characters")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

}