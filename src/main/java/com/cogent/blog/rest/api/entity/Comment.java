package com.cogent.blog.rest.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "comments"
)
public class Comment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @NotEmpty(message="name should not be empty or null")
    @Size(min=2, message = "name should be at least two characters long")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message="email should not be empty or null")
    @Size(min=5, message = "email should be at least five characters long")
    @Column(name = "email", nullable = false)
    private String email;

    @NotEmpty(message="body should not be empty or null")
    @Size(min=10, message = "body should be at least ten characters long")
    @Column(name = "body", nullable = false)
    private String body;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "post_id",
            nullable = false
    )
    private Post post;
}
