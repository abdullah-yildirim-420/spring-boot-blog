package com.blog.blogspot.controller;

import com.blog.blogspot.dto.CommentRequestDTO;
import com.blog.blogspot.dto.CommentResponseDTO;
import com.blog.blogspot.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> create(@PathVariable Long postId,
                                                     @RequestBody @Valid CommentRequestDTO commentRequestDTO,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(postId, commentRequestDTO, userDetails.getUsername()));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<Page<CommentResponseDTO>> getAllComments(@PathVariable Long postId,
                                                                   @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok().body(commentService.getAllComments(postId,pageable));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long commentId,
                                                            @RequestBody @Valid CommentRequestDTO commentRequestDTO,
                                                            @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok().body(commentService.updateComment(commentId,commentRequestDTO,userDetails.getUsername()));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetails userDetails){
        commentService.deleteComment(commentId, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }


}
