package com.blog.blogspot.repository;

import com.blog.blogspot.entity.Follow;
import com.blog.blogspot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowerAndFollowing(Person follower, Person following);
}