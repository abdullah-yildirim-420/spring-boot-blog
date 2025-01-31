package com.blog.blogspot.service.impl;

import com.blog.blogspot.dto.ReactionRequestDTO;
import com.blog.blogspot.entity.Person;
import com.blog.blogspot.entity.Post;
import com.blog.blogspot.entity.PostReaction;
import com.blog.blogspot.mapper.PostReactionMapper;
import com.blog.blogspot.repository.PersonRepository;
import com.blog.blogspot.repository.PostReactionRepository;
import com.blog.blogspot.repository.PostRepository;
import com.blog.blogspot.service.PostReactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostReactionServiceImpl implements PostReactionService {

    private final PostReactionRepository postReactionRepository;
    private final PersonRepository personRepository;
    private final PostRepository postRepository;
    private final PostReactionMapper postReactionMapper;

    public PostReactionServiceImpl(PostReactionRepository postReactionRepository,
                                   PersonRepository personRepository,
                                   PostRepository postRepository,
                                   PostReactionMapper postReactionMapper) {
        this.postReactionRepository = postReactionRepository;
        this.personRepository = personRepository;
        this.postRepository = postRepository;
        this.postReactionMapper = postReactionMapper;
    }

    @Transactional
    @Override
    public void reactToPost(Long postId, ReactionRequestDTO reactionRequestDTO, UserDetails userDetails) {

        Person person = personRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Person not found with given username."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with given id."));

        PostReaction postReaction = postReactionRepository.findByPersonAndPost(person, post)
                .orElse(null);

        if (postReaction == null) {
            postReaction = postReactionMapper.toEntity(reactionRequestDTO, person, post);
            postReactionRepository.save(postReaction);
        } else if (postReaction.getReactionType().equals(reactionRequestDTO.getReactionType())) {
            postReactionRepository.delete(postReaction);
        } else {
            postReaction.setReactionType(reactionRequestDTO.getReactionType());
            postReactionRepository.save(postReaction);
        }
    }
}
