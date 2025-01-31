package com.blog.blogspot.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface FollowService {

    void toggleFollow(String usernameToFollow, UserDetails userDetails);
}
