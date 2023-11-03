package com.pwee.eventmanagmentapp.entity;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Event {

    private Long id;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String description;

    private String link;

//    @ManyToOne
    private User coordinator;

//    @OneToMany(mappedBy = "coordinator")
//    private List<Event> organizedEvents;
}
