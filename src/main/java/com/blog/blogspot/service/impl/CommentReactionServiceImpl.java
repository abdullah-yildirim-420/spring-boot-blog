package com.blog.blogspot.service.impl;

import com.blog.blogspot.dto.ReactionRequestDTO;
import com.blog.blogspot.entity.Comment;
import com.blog.blogspot.entity.CommentReaction;
import com.blog.blogspot.entity.Person;
import com.blog.blogspot.mapper.CommentReactionMapper;
import com.blog.blogspot.repository.CommentReactionRepository;
import com.blog.blogspot.repository.CommentRepository;
import com.blog.blogspot.repository.PersonRepository;
import com.blog.blogspot.service.CommentReactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentReactionServiceImpl implements CommentReactionService {

    private final CommentReactionRepository commentReactionRepository;
    private final PersonRepository personRepository;
    private final CommentRepository commentRepository;
    private final CommentReactionMapper commentReactionMapper;

    public CommentReactionServiceImpl(CommentReactionRepository commentReactionRepository,
                                      PersonRepository personRepository,
                                      CommentRepository commentRepository,
                                      CommentReactionMapper commentReactionMapper) {
        this.commentReactionRepository = commentReactionRepository;
        this.personRepository = personRepository;
        this.commentRepository = commentRepository;
        this.commentReactionMapper = commentReactionMapper;
    }

    @Transactional
    @Override
    public void reactToComment(Long commentId, ReactionRequestDTO reactionRequestDTO, UserDetails userDetails) {

        Person person = personRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Person not found with given username."));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with given id."));

        CommentReaction commentReaction = commentReactionRepository.findByPersonAndComment(person, comment)
                .orElse(null);

        if (commentReaction == null) {

            commentReaction = commentReactionMapper.toEntity(reactionRequestDTO, person, comment);
            commentReactionRepository.save(commentReaction);

        } else if (commentReaction.getReactionType().equals(reactionRequestDTO.getReactionType())) {

            commentReactionRepository.delete(commentReaction);

        } else {

            commentReaction.setReactionType(reactionRequestDTO.getReactionType());
            commentReactionRepository.save(commentReaction);
        }
    }
}
