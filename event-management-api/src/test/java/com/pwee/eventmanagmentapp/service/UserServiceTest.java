package com.pwee.eventmanagmentapp.service;


import com.pwee.eventmanagmentapp.dto.UserDTO;
import com.pwee.eventmanagmentapp.entity.User;
import com.pwee.eventmanagmentapp.exception.UserNotFoundException;
import com.pwee.eventmanagmentapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.pwee.eventmanagmentapp.factory.UserFactory.makeUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class UserServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnTheUserWhenUserWithThatIdIsInDB() {
        LOGGER.info("Running shouldReturnTheUserWhenUserWithThatIdIsInDB test...");

        //given
        User user = makeUser();
        Long id = user.getId();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        //when
        UserDTO fetchedUser = userService.getUserById(id);

        //then
        verify(userRepository, times(1)).findById(id);
        assertThat(fetchedUser.getId()).isEqualTo(user.getId());
        assertThat(fetchedUser.getName()).isEqualTo(user.getName());
        assertThat(fetchedUser.getSurname()).isEqualTo(user.getSurname());
        assertThat(fetchedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenUserWithThatIdIsNotInDB() {
        LOGGER.info("Running shouldThrowExceptionWhenUserWithThatIdIsNotInDB test...");

        //given
        Long notExistingUserId = 1L;
        when(userRepository.findById(notExistingUserId)).thenReturn(Optional.empty());

        //when
        ThrowableAssert.ThrowingCallable getUserByIdExecutable = () -> userService.getUserById(notExistingUserId);

        //then
        assertThatThrownBy(getUserByIdExecutable)
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User doesn't exist!");
        verify(userRepository, times(1)).findById(notExistingUserId);
    }

    @Test
    void shouldThrowExceptionWhenProvidedIdIsNull() {
        LOGGER.info("Running shouldThrowExceptionWhenProvidedIdIsNull test...");

        //given
        Long nullId = null;
        when(userRepository.findById(nullId)).thenThrow(IllegalArgumentException.class);

        //when
        ThrowableAssert.ThrowingCallable getUserByIdExecutable = () -> userService.getUserById(nullId);

        //then
        assertThatThrownBy(getUserByIdExecutable)
                .isInstanceOf(IllegalArgumentException.class);
        verify(userRepository, times(1)).findById(nullId);
    }

    @Test
    void shouldReturnAllUsersWhenGetAllUsersInvoked() {
        LOGGER.info("Running shouldReturnAllUsersWhenGetAllUsersInvoked test...");

        //given
        User user1 = makeUser();
        User user2 = makeUser().toBuilder()
                .id(2L)
                .name("Jamess")
                .email("u2@example.com")
                .surname("Jackson")
                .password("password")
                .build();

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        //when
        List<UserDTO> allUsers = userService.getAllUsers();

        //then
        verify(userRepository, times(1)).findAll();
        assertThat(allUsers).isNotNull();
        assertThat(allUsers).hasSize(2);
        assertThat(allUsers.get(0).getId()).isEqualTo(1L);
        assertThat(allUsers.get(1).getName()).isEqualTo("Jamess");
    }

    @Test
    void shouldReturnEmptyListWhenNoDataIsPresentInDB() {
        LOGGER.info("Running shouldReturnEmptyListWhenNoDataIsPresentInDB test...");

        //given
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        //when
        List<UserDTO> allUsers = userService.getAllUsers();

        //then
        verify(userRepository, times(1)).findAll();
        assertThat(allUsers).isNotNull();
        assertThat(allUsers).hasSize(0);
    }

    @Test
    void shouldCreateNewUserWhenGivenUserHasAllRequiredData() {
        LOGGER.info("Running shouldCreateNewUserWhenGivenUserHasAllRequiredData test...");

        //given
        User user = makeUser().toBuilder()
                .id(null).build();
        User dbUser = user.toBuilder()
                .id(5L).build();
        when(userRepository.save(user)).thenReturn(dbUser);

        //when
        UserDTO createdUser = userService.createUser(user);

        //then
        verify(userRepository, times(1)).save(user);
        assertThat(createdUser.getId()).isEqualTo(5L);
        assertThat(createdUser.getName()).isEqualTo(dbUser.getName());
        assertThat(createdUser.getSurname()).isEqualTo(dbUser.getSurname());
        assertThat(createdUser.getEmail()).isEqualTo(dbUser.getEmail());
    }

    @Test
    void shouldUpdateUserDataWhenGivenUserAlreadyExists() {
        LOGGER.info("Running shouldUpdateUserDataWhenGivenUserAlreadyExists test...");

        //given
        User user = makeUser();
        Long userId = user.getId();
        User toUpdate = user.toBuilder()
                .name("James")
                .email("james@example.com")
                .surname("Jackson")
                .password("123")
                .build();
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.save(toUpdate)).thenReturn(toUpdate);

        //when
        UserDTO updatedUser = userService.updateUser(toUpdate);

        //then
        verify(userRepository, times(1)).existsById(toUpdate.getId());
        verify(userRepository, times(1)).save(toUpdate);
        assertThat(updatedUser.getId()).isEqualTo(toUpdate.getId());
        assertThat(updatedUser.getName()).isEqualTo(toUpdate.getName());
        assertThat(updatedUser.getSurname()).isEqualTo(toUpdate.getSurname());
        assertThat(updatedUser.getEmail()).isEqualTo(toUpdate.getEmail());
    }

    @Test
    void shouldThrowExceptionOnUpdateWhenGivenUserDoesNotExist() {
        LOGGER.info("Running shouldThrowExceptionOnUpdateWhenGivenUserDoesNotExist test...");

        //given
        User notExistingUser = makeUser();

        when(userRepository.existsById(notExistingUser.getId())).thenReturn(false);

        //when
        ThrowableAssert.ThrowingCallable updateUserExecutable = () -> userService.updateUser(notExistingUser);

        //then
        assertThatThrownBy(updateUserExecutable)
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User has not been found!");
        verify(userRepository, times(1)).existsById(notExistingUser.getId());
        verify(userRepository, times(0)).save(notExistingUser);
    }

    @Test
    void shouldThrowExceptionOnUpdateWhenGivenUserIsNull() {
        LOGGER.info("Running shouldThrowExceptionOnUpdateWhenGivenUserIsNull test...");

        //given
        User nullUser = null;

        //when
        ThrowableAssert.ThrowingCallable updateUserExecutable = () -> userService.updateUser(nullUser);

        //then
        assertThatThrownBy(updateUserExecutable)
                .isInstanceOf(IllegalArgumentException.class);
        verify(userRepository, times(0)).existsById(any());
        verify(userRepository, times(0)).save(nullUser);
    }

    @Test
    void shouldDeleteUserWhenUserWithGivenIdExists() {
        LOGGER.info("Running shouldDeleteUserWhenUserWithGivenIdExists test...");

        //given
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        //when
        userService.deleteUser(userId);

        //then
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void shouldNotBeExceptionWhenTryingToDeleteNotExistingUser() {
        LOGGER.info("Running shouldNotBeExceptionWhenTryingToDeleteNotExistingUser test...");

        //given
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        //when
        userService.deleteUser(userId);

        //then
        verify(userRepository, times(1)).deleteById(userId);
    }


}





























