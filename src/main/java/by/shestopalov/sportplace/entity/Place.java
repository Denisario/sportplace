package by.shestopalov.sportplace.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    private Long id;
    private String country;
    private String city;
    private String street;
    private Integer number;
    private List<Event> events;
}
