package com.blog.blogspot.service.impl;

import com.blog.blogspot.dto.CommentRequestDTO;
import com.blog.blogspot.dto.CommentResponseDTO;
import com.blog.blogspot.entity.Comment;
import com.blog.blogspot.entity.Person;
import com.blog.blogspot.entity.Post;
import com.blog.blogspot.mapper.CommentMapper;
import com.blog.blogspot.repository.CommentRepository;
import com.blog.blogspot.repository.PersonRepository;
import com.blog.blogspot.repository.PostRepository;
import com.blog.blogspot.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final PersonRepository personRepository;


    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, CommentMapper commentMapper, PersonRepository personRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postRepository = postRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    @Override
    public CommentResponseDTO create(Long postId, CommentRequestDTO commentRequestDTO, String username) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with given id."));

        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Person not found with given username."));

        Comment comment = commentMapper.toEntity(commentRequestDTO, person, post);

        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CommentResponseDTO> getAllComments(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable)
                .map(commentMapper::toDto);
    }

    @Transactional
    @Override
    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO commentRequestDTO, String username) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with given id."));

        if (!username.equals(comment.getPerson().getUsername())) {
            throw new AccessDeniedException("You are not authorized to update this comment.");
        }

        comment.setContent(commentRequestDTO.getContent());
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);

        return commentMapper.toDto(comment);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId, String username) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with given id."));

        if (!comment.getPerson().getUsername().equals(username)) {
            throw new AccessDeniedException("You are not authorized to delete this comment.");
        }

        commentRepository.delete(comment);
    }



}
