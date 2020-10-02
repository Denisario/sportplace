package by.shestopalov.sportplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
    private String name;
    private String country;
    private String city;
    private String street;
    private Integer number;
}
