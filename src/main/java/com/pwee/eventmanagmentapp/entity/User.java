package com.pwee.eventmanagmentapp.entity;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class User {

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
    @Size(min=8)
    private String password;

}
