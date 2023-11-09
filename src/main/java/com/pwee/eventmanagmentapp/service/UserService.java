package com.pwee.eventmanagmentapp.service;

import com.pwee.eventmanagmentapp.dto.UserCreationDTO;
import com.pwee.eventmanagmentapp.dto.UserDTO;
import com.pwee.eventmanagmentapp.entity.User;
import com.pwee.eventmanagmentapp.exception.UserNotFoundException;
import com.pwee.eventmanagmentapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO getUserById(Long userId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User doesn't exist!"));

        return UserDTO
                .builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }

    public User getUserWithAllFields(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User doesn't exist!"));
    }

    public List<UserDTO> getAllUsers() {

        return userRepository
                .findAll()
                .stream()
                .map((user -> new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getEmail())))
                .toList();
    }

    public UserDTO createUser(User userDTO) {

        if(userDTO == null) {
            throw new IllegalArgumentException("Can't create user from null :(");
        }

        User user = User
                .builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

        User createdUser = userRepository.save(user);
        return UserDTO
                .builder()
                .id(createdUser.getId())
                .name(createdUser.getName())
                .surname(createdUser.getSurname())
                .email(createdUser.getEmail())
                .build();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

//    public UserDTO updateUser(User updatedUser) {
//        if (updatedUser == null) {
//            throw new IllegalArgumentException("User cannot be null!");
//        }
//        if(!userRepository.existsById(updatedUser.getId())) {
//            throw new UserNotFoundException("User has not been found! Nothing updated.");
//        }
//
//        userRepository.save(updatedUser);
//
//        return UserDTO
//                .builder()
//                .id(updatedUser.getId())
//                .name(updatedUser.getName())
//                .surname(updatedUser.getSurname())
//                .email(updatedUser.getEmail())
//                .build();
//    }

    public UserDTO updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null!");
        }
        if(!userRepository.existsById(user.getId())) {
            throw new UserNotFoundException("User has not been found! Nothing updated.");
        }

        userRepository.save(user);

        return UserDTO
                .builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }
}
