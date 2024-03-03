package com.udemyspringcourse.restfulwebservices.repository;

import com.udemyspringcourse.restfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
