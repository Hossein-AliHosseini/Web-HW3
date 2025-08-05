package com.loupiart.model;

import jakarta.persistence.*;

@Entity
@Table(name = "drawing")
public class Drawing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", unique = true)
    private User user;
    @Lob
    private String content;

    public Drawing() {
    }
    public Drawing(User user, String content) {
        this.user = user;
        this.content = content;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
