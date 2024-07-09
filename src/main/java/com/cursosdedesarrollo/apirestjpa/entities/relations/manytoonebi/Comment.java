package com.cursosdedesarrollo.apirestjpa.entities.relations.manytoonebi;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String text;

    @JsonBackReference
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    // Getters and Setters (Omitted for brevity)
}
