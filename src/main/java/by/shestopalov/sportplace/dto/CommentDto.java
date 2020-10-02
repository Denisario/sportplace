package by.shestopalov.sportplace.dto;

import by.shestopalov.sportplace.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Double rating;
    private String text;
    private User user;
    private String eventId;
}
