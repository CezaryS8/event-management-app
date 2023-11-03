package com.pwee.eventmanagmentapp.repository;

import com.pwee.eventmanagmentapp.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MockUserRepository implements UserRepository {

    private final Map<Long, User> users = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User findUserById(Long id) {
        return users.get(id);
    }

    @Override
    public User saveUser(User user) {
        if(user.getId() == null) {
            user.setId(idCounter);
            idCounter++;
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void deleteUserById(Long id) {
        users.remove(id);
    }
}
