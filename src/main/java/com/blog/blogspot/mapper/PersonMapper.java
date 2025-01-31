package com.blog.blogspot.mapper;

import com.blog.blogspot.dto.RegisterRequestDTO;
import com.blog.blogspot.entity.Person;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    private final PasswordEncoder passwordEncoder;

    public PersonMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public Person toEntity(RegisterRequestDTO registerRequestDTO) {
        Person person = new Person();
        person.setFirstName(registerRequestDTO.getFirstName());
        person.setLastName(registerRequestDTO.getLastName());
        person.setUsername(registerRequestDTO.getUsername());
        person.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        person.setEmail(registerRequestDTO.getEmail());
        person.setPhoneNumber(registerRequestDTO.getPhoneNumber());
        person.setDateOfBirth(registerRequestDTO.getDateOfBirth());
        person.setRole(registerRequestDTO.getRole()); // Rol atanıyor
        return person;
    }

}
