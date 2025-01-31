package com.blog.blogspot.service;

import com.blog.blogspot.dto.ReactionRequestDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface CommentReactionService {
    void reactToComment(Long commentId, ReactionRequestDTO reactionRequestDTO, UserDetails userDetails);
}
