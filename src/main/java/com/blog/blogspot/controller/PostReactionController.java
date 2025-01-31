package com.blog.blogspot.controller;

import com.blog.blogspot.dto.ReactionRequestDTO;
import com.blog.blogspot.service.PostReactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/reactions")
public class PostReactionController {

    private final PostReactionService postReactionService;

    public PostReactionController(PostReactionService postReactionService) {
        this.postReactionService = postReactionService;
    }

    @PostMapping
    public ResponseEntity<Void> reactToPost(@PathVariable Long postId,
                                            @RequestBody ReactionRequestDTO reactionRequestDTO,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        postReactionService.reactToPost(postId, reactionRequestDTO, userDetails);
        return ResponseEntity.ok().build();
    }
}
