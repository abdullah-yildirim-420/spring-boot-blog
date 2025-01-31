package com.blog.blogspot.mapper;

import com.blog.blogspot.dto.CommentRequestDTO;
import com.blog.blogspot.dto.CommentResponseDTO;
import com.blog.blogspot.entity.Comment;
import com.blog.blogspot.entity.Person;
import com.blog.blogspot.entity.Post;
import com.blog.blogspot.enums.ReactionType;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(CommentRequestDTO commentRequestDTO, Person person, Post post) {
        return new Comment(commentRequestDTO.getContent(), person, post);
    }


    public CommentResponseDTO toDto(Comment comment) {
        return new CommentResponseDTO(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getPerson().getUsername(),
                comment.getPerson().getFirstName(),
                comment.getPerson().getLastName(),
                comment.getReactions().stream().filter(r -> r.getReactionType() == ReactionType.UPVOTE).count(),
                comment.getReactions().stream().filter(r -> r.getReactionType() == ReactionType.DOWNVOTE).count()
        );
    }
}