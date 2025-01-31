package com.blog.blogspot.controller;

import com.blog.blogspot.dto.ReactionRequestDTO;
import com.blog.blogspot.service.CommentReactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments/{commentId}/reactions")
public class CommentReactionController {

    private final CommentReactionService commentReactionService;

    public CommentReactionController(CommentReactionService commentReactionService) {
        this.commentReactionService = commentReactionService;
    }

    @PostMapping
    public ResponseEntity<Void> reactToComment(@PathVariable Long commentId,
                                               @RequestBody ReactionRequestDTO reactionRequestDTO,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        commentReactionService.reactToComment(commentId, reactionRequestDTO, userDetails);
        return ResponseEntity.ok().build();
    }
}
