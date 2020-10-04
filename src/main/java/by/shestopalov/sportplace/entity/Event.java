package by.shestopalov.sportplace.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"place"})
@EqualsAndHashCode(exclude = {"place", "comments"})
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finishDate;
    private Place place;
    private Set<Comment> comments;
}
