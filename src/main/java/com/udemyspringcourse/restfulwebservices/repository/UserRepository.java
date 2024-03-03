package com.udemyspringcourse.restfulwebservices.repository;

import com.udemyspringcourse.restfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
