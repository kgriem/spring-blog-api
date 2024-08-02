package com.cogent.blog.rest.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "posts",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "title"
                )
        }
)
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @NotEmpty(message="title should not be empty or null")
    @Size(min=2, message = "title should be at least two characters long")
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty(message="description should not be empty or null")
    @Size(min=10, message = "title should be at least ten characters long")
    @Column(name = "description", nullable = false)
    private String description;

    @NotEmpty(message="content should not be empty or null")
    @Size(min=10, message = "title should be at least ten characters long")
    @Column(name = "content", nullable = false)
    private String content;
/*
    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Comment> comments = new HashSet<>();
 */
}
