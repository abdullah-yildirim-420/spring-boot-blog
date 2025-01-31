package com.blog.blogspot.service;

import com.blog.blogspot.dto.PostRequestDTO;
import com.blog.blogspot.dto.PostResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface PostService {
    @Transactional
    PostResponseDTO create(PostRequestDTO postRequestDTO, String username);

    @Transactional
    PostResponseDTO getPostById(Long postId);

    @Transactional
    Page<PostResponseDTO> getPosts(String username, Pageable pageable);

    @Transactional
    void deletePostById(Long postId, String username);

    @Transactional
    PostResponseDTO updatePost(Long postId, String username, PostRequestDTO postRequestDTO);
}
