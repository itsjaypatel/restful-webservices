package com.udemyspringcourse.restfulwebservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Reaction {

    @Id
    @GeneratedValue
    private Integer id;

    private EMOJI emoji;

    @JsonIgnore
    private Integer userId;

    @JsonIgnore
    private Integer postId;

    public Reaction(){

    }

    public Reaction(EMOJI emoji, Integer userId, Integer postId) {
        this.emoji = emoji;
        this.userId = userId;
        this.postId = postId;
    }

    public EMOJI getEmoji() {
        return emoji;
    }

    public void setEmoji(EMOJI emoji) {
        this.emoji = emoji;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Reaction{" +
                "emoji=" + emoji +
                '}';
    }
}
