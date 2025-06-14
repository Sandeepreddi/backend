package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "blog-users")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private List<Blog> blogs;

    public User() {
    }

    public User(String id, String name, String email, List<Blog> blogs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.blogs = blogs;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
       this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
       this.email = email;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    // Static nested Blog class
    public static class Blog {
        private String title;
        private String content;
        private LocalDateTime createdAt;

        public Blog() {
            this.createdAt = LocalDateTime.now();
        }

        public Blog(String title, String content) {
            this.title = title;
            this.content = content;
            this.createdAt = LocalDateTime.now();
        }

        // Getters and Setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
           this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
           this.content = content;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }
    }
}
