package com.pwee.eventmanagmentapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    @NotNull
    private Long id;

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @Size(min=2, max=30)
    private String surname;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min=8, max=30)
    private String password;
}
