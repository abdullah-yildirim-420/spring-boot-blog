package com.blog.blogspot.service.impl;

import com.blog.blogspot.entity.Person;
import com.blog.blogspot.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    public CustomUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Person not found with given username."));

        return new org.springframework.security.core.userdetails.User(
                person.getUsername(),
                person.getPassword(),
                person.getRole().getAuthorities()
        );
    }
}
