package com.blog.blogspot.dto;

import com.blog.blogspot.enums.ReactionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReactionRequestDTO {
    @NotNull
    private ReactionType reactionType;

}