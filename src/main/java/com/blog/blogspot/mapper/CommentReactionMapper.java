package com.blog.blogspot.mapper;

import com.blog.blogspot.dto.ReactionRequestDTO;
import com.blog.blogspot.entity.Comment;
import com.blog.blogspot.entity.CommentReaction;
import com.blog.blogspot.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class CommentReactionMapper {

    public CommentReaction toEntity(ReactionRequestDTO dto, Person person, Comment comment) {
        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setReactionType(dto.getReactionType());
        commentReaction.setPerson(person);
        commentReaction.setComment(comment);
        return commentReaction;
    }
}
