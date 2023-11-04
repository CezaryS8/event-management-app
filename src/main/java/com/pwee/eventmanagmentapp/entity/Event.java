package com.pwee.eventmanagmentapp.entity;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode
public class Event {

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

    private Long coordinatorId;
//    @ManyToOne
//    private User coordinator;

//    @OneToMany(mappedBy = "coordinator")
//    private List<Event> organizedEvents;
}
