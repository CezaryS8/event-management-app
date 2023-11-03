package com.pwee.eventmanagmentapp.entity;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class User {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

}
