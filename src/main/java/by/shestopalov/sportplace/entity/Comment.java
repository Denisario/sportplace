package by.shestopalov.sportplace.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "COMMENTS")
@Getter
@Setter
@ToString(exclude = {"user", "event"})
@EqualsAndHashCode(exclude = {"user", "event", "file"})
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
    @JoinColumn(name = "EVENTS_ID")
    @JsonManagedReference
    private Event event;
    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER,cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonManagedReference
    private Collection<File> file;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    @JsonManagedReference
    private User user;
}
