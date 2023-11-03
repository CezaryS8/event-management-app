package com.pwee.eventmanagmentapp.repository;

import com.pwee.eventmanagmentapp.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    List<User> findAllUsers();
    User findUserById(Long id);
    User saveUser(User user);
    void deleteUserById(Long id);
}
