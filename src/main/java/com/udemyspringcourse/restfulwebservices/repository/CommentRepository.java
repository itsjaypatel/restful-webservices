package com.udemyspringcourse.restfulwebservices.repository;

import com.udemyspringcourse.restfulwebservices.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
