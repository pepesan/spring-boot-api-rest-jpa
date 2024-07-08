package com.cursosdedesarrollo.apirestjpa.entities.relations.manytooneuni;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class PostUni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            // fetch = FetchType.EAGER
            fetch = FetchType.LAZY // no devuelve los comentarios
    )
    @JoinColumn(name = "post_id")
    private List<CommentUni> comments = new ArrayList<>();

    public void addComment(CommentUni comment) {
        this.comments.add(comment);
    }

    public void removeComment(CommentUni comment) {
        this.comments.remove(comment);
    }
}

