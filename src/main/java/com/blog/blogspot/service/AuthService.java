package com.blog.blogspot.service;

import com.blog.blogspot.dto.LoginRequestDTO;
import com.blog.blogspot.dto.RegisterRequestDTO;

public interface AuthService {
    void register(RegisterRequestDTO registerRequestDTO);

    String login(LoginRequestDTO loginRequestDTO);
}
