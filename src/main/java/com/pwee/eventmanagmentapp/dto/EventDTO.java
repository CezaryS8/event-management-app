package com.pwee.eventmanagmentapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class EventDTO {

    @NotNull
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
}
