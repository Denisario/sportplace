package by.shestopalov.sportplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
    @NotBlank(message = "Place name can not be null")
    @Size(min = 5, message = "Event name must contains at least 5 symbols")
    private String name;
    @NotBlank(message = "Country can not be null")
    private String country;
    @NotBlank(message = "City can not be null")
    private String city;
    @NotBlank(message = "Street can not be null")
    private String street;
    @NotBlank(message = "Number of building can not be null")
    private String number;
}
