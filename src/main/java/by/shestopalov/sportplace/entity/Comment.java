package by.shestopalov.sportplace.entity;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"user", "event"})
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private Short rating;
    private String text;
    private User user;
    private Event event;
}
