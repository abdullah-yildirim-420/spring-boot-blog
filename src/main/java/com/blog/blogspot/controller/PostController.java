package com.blog.blogspot.controller;


import com.blog.blogspot.dto.PostRequestDTO;
import com.blog.blogspot.dto.PostResponseDTO;
import com.blog.blogspot.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<PostResponseDTO> create(@Valid @RequestBody PostRequestDTO postRequestDTO,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.created(null).body(postService.create(postRequestDTO, userDetails.getUsername()));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok().body(postService.getPostById(postId));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long postId, @AuthenticationPrincipal UserDetails userDetails) {
        postService.deletePostById(postId, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetails userDetails,
                                                      @RequestBody @Valid PostRequestDTO postRequestDTO) {
        PostResponseDTO postResponseDTO = postService.updatePost(postId, userDetails.getUsername(), postRequestDTO);
        return ResponseEntity.ok().body(postResponseDTO);
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDTO>> getPosts(@RequestParam(required = false) String username,
                                                          @PageableDefault(size = 10, sort = "createdAt",
                                                          direction = Sort.Direction.DESC) Pageable pageable) {

        Page<PostResponseDTO> posts = postService.getPosts(username, pageable);
        return ResponseEntity.ok(posts);
    }
}