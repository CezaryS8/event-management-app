package com.pwee.eventmanagmentapp.service;

import com.pwee.eventmanagmentapp.entity.User;
import com.pwee.eventmanagmentapp.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private List<User> userRepository = new ArrayList<>();

    public User getUserById(Integer userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User doesn't exist!"), HttpStatus.NOT_FOUND));
    }

}
