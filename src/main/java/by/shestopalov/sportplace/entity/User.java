package by.shestopalov.sportplace.entity;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private Set<Comment> comments;

}
