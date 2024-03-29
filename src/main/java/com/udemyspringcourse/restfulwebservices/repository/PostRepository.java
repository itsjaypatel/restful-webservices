package com.udemyspringcourse.restfulwebservices.repository;

import com.udemyspringcourse.restfulwebservices.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
}
