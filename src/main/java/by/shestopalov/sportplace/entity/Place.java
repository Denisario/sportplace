package by.shestopalov.sportplace.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
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
