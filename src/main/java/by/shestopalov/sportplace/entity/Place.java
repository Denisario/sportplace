package by.shestopalov.sportplace.entity;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"events"})
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private Integer number;
    private Set<Event> events;
}
