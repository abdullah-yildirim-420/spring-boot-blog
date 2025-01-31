package com.blog.blogspot.entity;

import com.blog.blogspot.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String firstName;

    @Column(nullable = false, length = 32)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @ToString.Exclude
    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 32)
    private String email;

    @Column(nullable = false, unique = true, length = 16)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY)
    private List<Follow> following;

    @OneToMany(mappedBy = "following", fetch = FetchType.LAZY)
    private List<Follow> followers;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastOnline;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<PostReaction> postReactions;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<CommentReaction> commentReactions;

}
