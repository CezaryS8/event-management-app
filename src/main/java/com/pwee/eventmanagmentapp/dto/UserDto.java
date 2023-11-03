package com.pwee.eventmanagmentapp.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;
}
