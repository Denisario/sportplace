package by.shestopalov.sportplace.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "FILES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID", nullable = false)
    private Long id;
    @Column(name = "FILE_FILENAME", nullable = false)
    private String filename;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;
}
