package com.blog.blogspot.repository;

import com.blog.blogspot.entity.PostReaction;
import com.blog.blogspot.entity.Person;
import com.blog.blogspot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostReactionRepository extends JpaRepository<PostReaction, Long> {
    Optional<PostReaction> findByPersonAndPost(Person person, Post post);
}
