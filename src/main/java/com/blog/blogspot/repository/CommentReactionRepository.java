package com.blog.blogspot.repository;

import com.blog.blogspot.entity.CommentReaction;
import com.blog.blogspot.entity.Person;
import com.blog.blogspot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {
    Optional<CommentReaction> findByPersonAndComment(Person person, Comment comment);
}
