package com.udemyspringcourse.restfulwebservices.dao;

import com.udemyspringcourse.restfulwebservices.entity.User;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserDaoService {

    private static int sequence = 1;

    private static Map<Integer,User> users = new HashMap<>();

    static{
        users.put(sequence,new User(sequence,"Jay", LocalDate.parse("2001-04-17")));
        updateSequence();
        users.put(sequence,new User(sequence,"Raj", LocalDate.parse("2000-10-04")));
        updateSequence();
        users.put(sequence,new User(sequence,"Rahul", LocalDate.parse("2002-05-12")));
        updateSequence();
    }


    public List<User> findAll(){
        return users.values().stream().toList();
    }

    public User findById(int userId){
        return users.values().stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
    }

    public User save(User user){
        user.setId(sequence);
        users.put(sequence,user);
        updateSequence();
        return user;
    }

    public boolean deleteById(int userId){
        if(users.containsKey(userId)){
            users.remove(userId);
            return true;
        }
        return false;
    }

    private static void updateSequence(){
        sequence++;
    }

}
