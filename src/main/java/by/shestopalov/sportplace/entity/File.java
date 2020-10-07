package by.shestopalov.sportplace.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity(name = "FILES")
@Getter
@Setter
@ToString(exclude = {"comment"})
@EqualsAndHashCode(exclude = {"comment"})
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID", nullable = false)
    private Long id;
    @Column(name = "FILE_FILENAME", nullable = false)
    private String filename;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "COMMENT_ID")
    @JsonBackReference
    private Comment comment;
}
