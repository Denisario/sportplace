package by.shestopalov.sportplace.dto;

import by.shestopalov.sportplace.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    @NotNull(message = "Enter the rating")
    private Integer rating;
    @NotBlank(message = "Enter text")
    @Size(min = 10, max = 150, message = "Text size between 10 and 150 symbols")
    private String text;
    private User user;
    private String eventId;
    private Set<String> filename;
}
