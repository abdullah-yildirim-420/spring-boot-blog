package com.blog.blogspot.mapper;

import com.blog.blogspot.dto.ReactionRequestDTO;
import com.blog.blogspot.entity.Person;
import com.blog.blogspot.entity.Post;
import com.blog.blogspot.entity.PostReaction;
import org.springframework.stereotype.Component;

@Component
public class PostReactionMapper {

    public PostReaction toEntity(ReactionRequestDTO dto, Person person, Post post) {
        PostReaction postReaction = new PostReaction();
        postReaction.setReactionType(dto.getReactionType());
        postReaction.setPerson(person);
        postReaction.setPost(post);
        return postReaction;
    }
}
