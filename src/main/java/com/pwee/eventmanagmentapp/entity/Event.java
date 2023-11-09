package com.pwee.eventmanagmentapp.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_generator")
    private Long id;

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @DateTimeFormat
    private LocalDateTime startDate;

    @NotNull
    @DateTimeFormat
    private LocalDateTime endDate;

    @NotNull
    private String description;

    private String link;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private User user;
}
