package com.pwee.eventmanagmentapp.factory;

import com.pwee.eventmanagmentapp.dto.UserDTO;
import com.pwee.eventmanagmentapp.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserFactory {

	public static User makeUser() {
		return User
			.builder()
			.id(1L)
			.name("James")
			.email("james@mail.com")
			.surname("Bond")
			.password("password")
			.events(Collections.emptyList())
			.build();
	}

	public static UserDTO makeUserDTO() {
		return UserDTO
				.builder()
				.name("James")
				.email("james@mail.com")
				.surname("Bond")
				.build();
	}
}
