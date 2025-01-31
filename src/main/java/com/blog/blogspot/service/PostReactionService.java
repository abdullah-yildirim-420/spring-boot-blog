package com.blog.blogspot.service;

import com.blog.blogspot.dto.ReactionRequestDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface PostReactionService {
    void reactToPost(Long postId, ReactionRequestDTO reactionRequestDTO, UserDetails userDetails);
}
