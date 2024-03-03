package com.udemyspringcourse.restfulwebservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Post postInfo;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User userInfo;

    @Transient
    private Map<Object,Object> refs;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Comment(){
    }

    public Comment(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Post getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(Post postInfo) {
        this.postInfo = postInfo;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public Map<Object, Object> getRefs() {
        return refs;
    }

    public void setRefs(Map<Object, Object> refs) {
        this.refs = refs;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
