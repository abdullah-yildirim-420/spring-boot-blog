package com.blog.blogspot.controller;

import com.blog.blogspot.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/{usernameToFollow}")
    public ResponseEntity<Void> toggleFollow(@PathVariable String usernameToFollow,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        followService.toggleFollow(usernameToFollow, userDetails);
        return ResponseEntity.ok().build();
    }
}
