package com.blog.blogspot.service.impl;

import com.blog.blogspot.dto.LoginRequestDTO;
import com.blog.blogspot.dto.RegisterRequestDTO;
import com.blog.blogspot.entity.Person;
import com.blog.blogspot.exception.ConflictException;
import com.blog.blogspot.mapper.PersonMapper;
import com.blog.blogspot.repository.PersonRepository;
import com.blog.blogspot.service.AuthService;
import com.blog.blogspot.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final ValidationService validationService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil, PersonRepository personRepository, PersonMapper personMapper, ValidationService validationService){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.validationService = validationService;
    }

    @Override
    public void register(RegisterRequestDTO registerRequestDTO) {
        List<String> errors = validationService.validateRegisterFields(registerRequestDTO);

        if (!errors.isEmpty()) {
            throw new ConflictException(errors);
        }

        Person person = personMapper.toEntity(registerRequestDTO);
        personRepository.save(person);
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );
        return jwtUtil.generateToken(authentication.getName());
    }
}
