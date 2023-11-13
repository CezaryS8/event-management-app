package com.pwee.eventmanagmentapp.controller;

import com.pwee.eventmanagmentapp.dto.UserDTO;
import com.pwee.eventmanagmentapp.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerITest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    private User user;

    @AfterEach
    void tearDown() {
        ResponseEntity<UserDTO[]> allUsersResponse = makeGetAllUsersRequest();
        if (allUsersResponse != null && allUsersResponse.getBody() != null) {
            for (UserDTO user : allUsersResponse.getBody()) {
                makeUserDeletionRequest(user.getId());
            }
        }
    }

    @Nested
    @DisplayName("Create users tests")
    @Tag("POST")
    class CreateUserTests {
        @Test
        public void givenCorrectUser_whenSaveUser_thenReturnCreatedUserWithHttpStatusCreated() {
            // given
            User user = createTestUser();

            // when
            ResponseEntity<UserDTO> saveUserResponse = makeUserCreationRequest(user);

            // then
            assertEquals(HttpStatus.CREATED, saveUserResponse.getStatusCode());
            assertEquals(user.getName(), saveUserResponse.getBody().getName());
        }

        @Test
        public void givenIncorrectUserName_whenSaveUser_thenReturnCreatedUserWithHttpStatusCreated() {
            // given
            User user = createTestUser()
                    .toBuilder()
                    .name("")
                    .build();

            // when
            ResponseEntity<UserDTO> saveUserResponse = makeUserCreationRequest(user);

            // then
            assertEquals(HttpStatus.BAD_REQUEST, saveUserResponse.getStatusCode());
        }
        @Test
        public void givenIncorrectUserSurname_whenSaveUser_thenReturnCreatedUserWithHttpStatusCreated() {
            // given
            User user = createTestUser()
                    .toBuilder()
                    .surname("tolongsurnametolongsurnametolongbro")
                    .build();

            // when
            ResponseEntity<UserDTO> saveUserResponse = makeUserCreationRequest(user);

            // then
            assertEquals(HttpStatus.BAD_REQUEST, saveUserResponse.getStatusCode());
        }
        @Test
        public void givenIncorrectUserEmail_whenSaveUser_thenReturnCreatedUserWithHttpStatusCreated() {
            // given
            User user = createTestUser()
                    .toBuilder()
                    .email("@")
                    .build();

            // when
            ResponseEntity<UserDTO> saveUserResponse = makeUserCreationRequest(user);

            // then
            assertEquals(HttpStatus.BAD_REQUEST, saveUserResponse.getStatusCode());
        }

        @Test
        public void givenIncorrectUserPasswordLength_whenSaveUser_thenReturnCreatedUserWithHttpStatusCreated() {
            // given
            User user = createTestUser()
                    .toBuilder()
                    .password("short")
                    .build();

            // when
            ResponseEntity<UserDTO> saveUserResponse = makeUserCreationRequest(user);

            // then
            assertEquals(HttpStatus.BAD_REQUEST, saveUserResponse.getStatusCode());
        }

    }

    @Nested
    @DisplayName("Get users tests")
    @Tag("GET")
    class GetUserTests {
        @Test
        public void givenExistsUserId_whenGetUserById_thenReturnUserWithHttpStatusOk() {
            // given
            User user = createTestUser();
            ResponseEntity<UserDTO> saveUserResponse = makeUserCreationRequest(user);
            Long userId = saveUserResponse.getBody().getId();

            // when
            ResponseEntity<UserDTO> getUserByIdResponse = makeGetUserByIdRequest(userId);

            // then
            assertEquals(HttpStatus.OK, getUserByIdResponse.getStatusCode());
            assertEquals(user.getName(), getUserByIdResponse.getBody().getName());
        }


        @Test
        public void givenNotExistsUserId_whenGetUserById_thenReturnHttpStatusNotFound() {
            // given
            Long userId = 10L;

            // when
            ResponseEntity<UserDTO> getUserByIdResponse = makeGetUserByIdRequest(userId);

            // then
            assertEquals(HttpStatus.NOT_FOUND, getUserByIdResponse.getStatusCode());
            assertNull(getUserByIdResponse.getBody());
        }
        @Test
        public void givenExistingUsers_whenGetAllUsers_thenReturnAllUsersWithHttpStatusOk() {
            // given
            User user1 = createTestUser();
            User user2 = createTestUser();
            ResponseEntity<UserDTO> saveUserResponse1 = makeUserCreationRequest(user1);
            ResponseEntity<UserDTO> saveUserResponse2 = makeUserCreationRequest(user2);

            // when
            ResponseEntity<UserDTO[]> allUsersResponse = makeGetAllUsersRequest();

            // then
            int expectedNumberOfUsers = 2;
            assertEquals(HttpStatus.OK, allUsersResponse.getStatusCode());
            assertEquals(expectedNumberOfUsers, Objects.requireNonNull(allUsersResponse.getBody()).length);
        }

        @Test
        public void givenNoUsers_whenGetAllUsers_thenReturnEmptyListWithHttpStatusOk() {
            // given

            // when
            ResponseEntity<UserDTO[]> allUsersResponse = makeGetAllUsersRequest();

            // then
            int expectedNumberOfUsers = 0;
            assertEquals(HttpStatus.OK, allUsersResponse.getStatusCode());
            assertEquals(expectedNumberOfUsers, Objects.requireNonNull(allUsersResponse.getBody()).length);
        }
    }
    @Nested
    @DisplayName("Update users tests")
    @Tag("UPDATE")
    class UpdateUserTests {
        @Test
        public void givenValidUserData_whenUpdateUser_thenReturnUpdatedUserWithHttpStatusOk() {
            // given
            User user = createTestUser();
            ResponseEntity<UserDTO> saveUserResponse = makeUserCreationRequest(user);

            User updatedUser = User
                    .builder()
                    .id(saveUserResponse.getBody().getId())
                    .name(saveUserResponse.getBody().getName())
                    .surname(saveUserResponse.getBody().getSurname())
                    .email(saveUserResponse.getBody().getEmail())
                    .password("newpassword")
                    .build();

            // when
            ResponseEntity<UserDTO> updateUserResponse = restTemplate.exchange(
                    "http://localhost:" + port + "/api/v1/users/",
                    HttpMethod.PUT,
                    new HttpEntity<>(updatedUser),
                    UserDTO.class
            );

            // then
            assertEquals(HttpStatus.OK, updateUserResponse.getStatusCode());
        }

        @Test
        public void givenInvalidUserData_whenUpdateUser_thenReturnUpdatedUserWithHttpStatusOk() {
            // given
            User user = createTestUser();
            ResponseEntity<UserDTO> saveUserResponse = makeUserCreationRequest(user);
            Long userId = saveUserResponse.getBody().getId();

            user.setName("");

            // when
            ResponseEntity<UserDTO> updateUserResponse = restTemplate.exchange(
                    "http://localhost:" + port + "/api/v1/users/",
                    HttpMethod.PUT,
                    new HttpEntity<>(user),
                    UserDTO.class
            );

            // then
            assertEquals(HttpStatus.BAD_REQUEST, updateUserResponse.getStatusCode());
        }
        @Test
        public void givenNullAsUserData_whenUpdateUser_thenReturnUpdatedUserWithHttpStatusOk() {
            // given
            User user = createTestUser();
            ResponseEntity<UserDTO> saveUserResponse = makeUserCreationRequest(user);
            Long userId = saveUserResponse.getBody().getId();

            UserDTO nullUser = null;

            // when
            ResponseEntity<UserDTO> updateUserResponse = restTemplate.exchange(
                    "http://localhost:" + port + "/api/v1/users/",
                    HttpMethod.PUT,
                    new HttpEntity<>(nullUser),
                    UserDTO.class
            );

            // then
            assertEquals(HttpStatus.BAD_REQUEST, updateUserResponse.getStatusCode());
        }
        @Test
        public void givenNullAsUserDataAndNotExistedUserId_whenUpdateUser_thenReturnUpdatedUserWithHttpStatusOk() {
            // given
            UserDTO user = null;
            Long userId = 100L;

            // when
            ResponseEntity<UserDTO> updateUserResponse = restTemplate.exchange(
                    "http://localhost:" + port + "/api/v1/users/",
                    HttpMethod.PUT,
                    new HttpEntity<>(user),
                    UserDTO.class
            );

            // then
            assertEquals(HttpStatus.BAD_REQUEST, updateUserResponse.getStatusCode());
        }
    }

    @Nested
    @DisplayName("Delete users tests")
    @Tag("DELETE")
    class DeleteUserTests {
        @Test
        public void givenExistsUserId_whenDeleteUser_thenUserIsDeleted() {
            // given
            User user = createTestUser();
            ResponseEntity<UserDTO> saveUserResponse = makeUserCreationRequest(user);
            Long userId = saveUserResponse.getBody().getId();

            // when
            ResponseEntity<Void> deleteUserResponse = makeUserDeletionRequest(userId);

            // then
            ResponseEntity<UserDTO> getUserByIdResponse = makeGetUserByIdRequest(userId);

            assertEquals(HttpStatus.NO_CONTENT, deleteUserResponse.getStatusCode());
            assertEquals(HttpStatus.NOT_FOUND, getUserByIdResponse.getStatusCode());
        }

        @Test
        public void givenNotExistsUserId_whenDeleteUser_thenUserIsNotDeleted() {
            // given
            User user = createTestUser();
            ResponseEntity<UserDTO> saveUserResponse = makeUserCreationRequest(user);
            Long userId = saveUserResponse.getBody().getId();
            Long noExistedUserId = 111L;

            // when
            ResponseEntity<Void> deleteUserResponse = makeUserDeletionRequest(noExistedUserId);

            // then
            ResponseEntity<UserDTO[]> allUsersResponse = makeGetAllUsersRequest();

            int expectedNumberOfUsers = 1;
            assertEquals(expectedNumberOfUsers, Objects.requireNonNull(allUsersResponse.getBody()).length);
            assertEquals(HttpStatus.NO_CONTENT, deleteUserResponse.getStatusCode());
        }
    }

    private User createTestUser() {
        return User
                .builder()
                .name("James")
                .surname("Bond")
                .email("jamesbond@example.com")
                .password("mysecurepassword")
                .build();
    }

    private ResponseEntity<UserDTO> makeGetUserByIdRequest(Long userId) {
        return restTemplate.getForEntity(
                "http://localhost:" + port + "/api/v1/users/" + userId,
                UserDTO.class
        );
    }
    private ResponseEntity<UserDTO[]> makeGetAllUsersRequest() {
        return restTemplate.getForEntity(
                "http://localhost:" + port + "/api/v1/users/",
                UserDTO[].class
        );
    }


    private ResponseEntity<UserDTO> makeUserCreationRequest(User testUser) {
        return restTemplate.postForEntity(
                "http://localhost:" + port + "/api/v1/users/",
                testUser,
                UserDTO.class
        );
    }
    private ResponseEntity<Void> makeUserDeletionRequest(Long userId) {

        return restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/users/" + userId,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

}
