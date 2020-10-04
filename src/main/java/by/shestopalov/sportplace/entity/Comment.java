package by.shestopalov.sportplace.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "COMMENTS")
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"user", "event"})
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID", nullable = false)
    private Long id;
    @Column(name = "COMMENT_RATING", nullable = false)
    private Short rating;
    @Column(name = "COMMENT_TEXT", nullable = false, length = 150)
    private String text;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "EVENTS_ID")
    private Event event;
    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER)
    private Collection<File> file;
}
