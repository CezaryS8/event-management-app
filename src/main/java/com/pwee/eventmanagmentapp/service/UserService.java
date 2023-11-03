package com.pwee.eventmanagmentapp.service;

import com.pwee.eventmanagmentapp.entity.User;
import com.pwee.eventmanagmentapp.exception.UserNotFoundException;
import com.pwee.eventmanagmentapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(Long userId) {
         User user = userRepository.findUserById(userId);
         if(user == null) {
             throw new UserNotFoundException("User doesn't exist!");
         }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public User createUser(User user) {
        return userRepository.saveUser(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteUserById(userId);
    }

    public User updateUser(User user) {
        if(userRepository.findUserById(user.getId()) == null) {
            throw new UserNotFoundException("User doesn't exist! Nothing updated.");
        }
        return userRepository.saveUser(user);
    }
}
