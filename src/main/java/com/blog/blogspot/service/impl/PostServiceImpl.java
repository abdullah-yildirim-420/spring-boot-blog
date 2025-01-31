package com.blog.blogspot.service.impl;

import com.blog.blogspot.dto.PostRequestDTO;
import com.blog.blogspot.dto.PostResponseDTO;
import com.blog.blogspot.entity.Post;
import com.blog.blogspot.mapper.PostMapper;
import com.blog.blogspot.repository.PostRepository;
import com.blog.blogspot.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.access.AccessDeniedException;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    @Transactional
    @Override
    public PostResponseDTO create(PostRequestDTO postRequestDTO, String username) {

        Post post = postMapper.toEntity(postRequestDTO, username);

        return postMapper.toDto(postRepository.save(post));
    }

    @Transactional
    @Override
    public PostResponseDTO getPostById(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("Post not found with given id."));

        return postMapper.toDto(post);
    }

    @Transactional
    @Override
    public Page<PostResponseDTO> getPosts(String username, Pageable pageable) {
        return (username != null)
                ? postRepository.findAllByPersonUsername(username, pageable).map(postMapper::toDto)
                : postRepository.findAll(pageable).map(postMapper::toDto);
    }

    @Transactional
    @Override
    public void deletePostById(Long postId, String username) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("Post not found with given id."));

        if(!post.getPerson().getUsername().equals(username)) {
            throw new AccessDeniedException("You are not authorized to delete this post.");
        }
        postRepository.delete(post);
    }

    @Transactional
    @Override
    public PostResponseDTO updatePost(Long postId, String username, PostRequestDTO postRequestDTO) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("Post not found with given id."));

        if(!post.getPerson().getUsername().equals(username)) {
            throw new AccessDeniedException("You are not authorized to update this post.");
        }

        postMapper.updatePost(post,postRequestDTO);

        return postMapper.toDto(post);
    }


}