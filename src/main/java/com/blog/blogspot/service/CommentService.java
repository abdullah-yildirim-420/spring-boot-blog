package com.blog.blogspot.service;

import com.blog.blogspot.dto.CommentRequestDTO;
import com.blog.blogspot.dto.CommentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    CommentResponseDTO create(Long postId, CommentRequestDTO commentRequestDTO, String username);

    Page<CommentResponseDTO> getAllComments(Long postId, Pageable pageable);
    
    CommentResponseDTO updateComment(Long commentId, CommentRequestDTO commentRequestDTO, String username);


    void deleteComment(Long commentId, String username);
}
