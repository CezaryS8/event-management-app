package com.pwee.eventmanagmentapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
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

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private List<Event> events = new ArrayList<>();
}
