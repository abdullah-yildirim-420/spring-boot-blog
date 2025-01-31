# Blog Application (Spring Boot)

## Introduction

This project is a **blogging platform** where users can:  
- **Create and manage blog posts**  
- **Comment on others' posts**  
- **React (like/dislike) to posts and comments** *(toggle system: clicking again removes the reaction)*  
- **Follow/unfollow other users** *(toggle system: clicking again unfollows the user)*  

The project is built using:  
- **Java 17** & **Spring Boot 3.3.2**  
- **Spring Data JPA** & **Hibernate** (Database interaction)  
- **PostgreSQL** (Database)  
- **Spring Security & JWT Authentication**  
- **Jakarta Validation API**  
- **Lombok** (To reduce boilerplate code)  

This project was created **as a learning experience** to explore backend development and apply best practices.  

---

## **API Endpoints**

### **Authentication**
- **POST /api/auth/register** → Register a new user  
  - Request Body: `RegisterRequestDTO`
  - Response: `201 Created`
- **POST /api/auth/login** → Login and get JWT token  
  - Request Body: `LoginRequestDTO`
  - Response: `{ "token": "JWT_TOKEN_HERE" }`

---

### **Post Endpoints**
- **POST /api/posts** → Create a new post  
  - Request Body: `PostRequestDTO`
  - Response: `PostResponseDTO`
- **GET /api/posts/{postId}** → Get post by ID  
- **GET /api/posts** → Get all posts  
  - Optional Query Parameter: `username` (to filter posts by user)
- **PUT /api/posts/{postId}** → Update a post  
- **DELETE /api/posts/{postId}** → Delete a post  

---

### **Comment Endpoints**
- **POST /api/posts/{postId}/comments** → Create a comment on a post  
  - Request Body: `CommentRequestDTO`
  - Response: `CommentResponseDTO`
- **GET /api/posts/{postId}/comments** → Get all comments on a post  
- **PUT /api/comments/{commentId}** → Update a comment  
- **DELETE /api/comments/{commentId}** → Delete a comment  

---

### **Reaction Endpoints (Toggle System)**
#### **Post Reactions**
- **POST /api/posts/{postId}/reactions** → Toggle reaction on a post  
  - If no reaction exists, it will be **added**.  
  - If the same reaction exists, it will be **removed**.  
  - If a different reaction exists, it will be **updated**.  
  - Request Body: `ReactionRequestDTO`

#### **Comment Reactions**
- **POST /api/comments/{commentId}/reactions** → Toggle reaction on a comment  
  - Works the same as **post reactions**.

---

### **Follow System (Toggle System)**
- **POST /api/follow/{usernameToFollow}** → Follow/unfollow a user  
  - If already following, it will **unfollow**.
  - If not following, it will **start following**.

---
