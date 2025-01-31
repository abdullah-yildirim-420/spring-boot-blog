package com.blog.blogspot.mapper;

import com.blog.blogspot.dto.PostRequestDTO;
import com.blog.blogspot.dto.PostResponseDTO;
import com.blog.blogspot.entity.Person;
import com.blog.blogspot.entity.Post;
import com.blog.blogspot.enums.ReactionType;
import com.blog.blogspot.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostMapper {

    private final PersonRepository personRepository;

    public PostMapper(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Post toEntity(PostRequestDTO postRequestDTO, String username){

        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Authenticated user not found: " + username));

        return new Post(postRequestDTO.getTitle(),postRequestDTO.getContent(),person);
    }

    public PostResponseDTO toDto(Post post){
        return new PostResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getPerson().getUsername(),
                post.getPerson().getFirstName(),
                post.getPerson().getLastName(),
                post.getReactions().stream().filter(r -> r.getReactionType() == ReactionType.UPVOTE).count(),
                post.getReactions().stream().filter(r -> r.getReactionType() == ReactionType.DOWNVOTE).count(),
                post.getComments().size()
        );
    }


    public void updatePost(Post post, PostRequestDTO postRequestDTO) {
        post.setTitle(postRequestDTO.getTitle());
        post.setContent(postRequestDTO.getContent());
        post.setUpdatedAt(LocalDateTime.now());
    }
}