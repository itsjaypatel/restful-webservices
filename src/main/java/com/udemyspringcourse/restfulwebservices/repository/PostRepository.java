package com.udemyspringcourse.restfulwebservices.repository;

import com.udemyspringcourse.restfulwebservices.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
