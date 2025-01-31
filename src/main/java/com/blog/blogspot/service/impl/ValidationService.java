package com.blog.blogspot.service.impl;

import com.blog.blogspot.dto.RegisterRequestDTO;
import com.blog.blogspot.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationService {

    private final PersonRepository personRepository;

    public ValidationService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<String> validateRegisterFields(RegisterRequestDTO registerRequestDTO) {
        List<String> errors = new ArrayList<>();

        if (personRepository.existsByEmail(registerRequestDTO.getEmail())) {
            errors.add("Email is already in use.");
        }

        if (personRepository.existsByUsername(registerRequestDTO.getUsername())) {
            errors.add("Username is already in use.");
        }

        if (personRepository.existsByPhoneNumber(registerRequestDTO.getPhoneNumber())) {
            errors.add("Phone number is already in use.");
        }

        return errors;
    }
}
