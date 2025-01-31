package com.blog.blogspot.service.impl;

import com.blog.blogspot.entity.Follow;
import com.blog.blogspot.entity.Person;
import com.blog.blogspot.repository.FollowRepository;
import com.blog.blogspot.repository.PersonRepository;
import com.blog.blogspot.service.FollowService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final PersonRepository personRepository;

    public FollowServiceImpl(FollowRepository followRepository, PersonRepository personRepository) {
        this.followRepository = followRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    @Override
    public void toggleFollow(String usernameToFollow, UserDetails userDetails) {

        Person follower = personRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Person not found with given username."));

        Person following = personRepository.findByUsername(usernameToFollow)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with given username."));

        if (follower.equals(following)) {
            throw new IllegalStateException("You cannot follow yourself.");
        }

        Optional<Follow> existingFollow = followRepository.findByFollowerAndFollowing(follower, following);

        if (existingFollow.isPresent()) {
            followRepository.delete(existingFollow.get());
        } else {
            Follow follow = new Follow();
            follow.setFollower(follower);
            follow.setFollowing(following);
            followRepository.save(follow);
        }
    }
}
