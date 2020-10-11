package by.shestopalov.sportplace.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "EVENTS")
@Getter
@Setter
@ToString(exclude = {"place"})
@EqualsAndHashCode(exclude = {"place", "comments"})
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID", nullable = false)
    private Long id;
    @Column(name = "EVENT_NAME", nullable = false)
    private String name;
    @Column(name = "EVENT_STARTDATE", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @Column(name = "EVENT_FINISHDATE", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finishDate;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "PLACE_ID")
    @JsonManagedReference
    private Place place;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonBackReference
    private Set<Comment> comments;
}
