package com.pwee.eventmanagmentapp.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 2, max = 255)
    private String name;

    @NotNull
    @Size(min = 2, max = 255)
    private String surname;

    @Email
    private String email;

    @NotNull
    @Size(min = 8, max = 255)
    private String password;
}
