package com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanyuni;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class NewsUni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToMany
    @JoinTable(
            name = "category_news_uni",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryUni> categoryList = new ArrayList<>();

    public void addCategory(CategoryUni category) {
        this.categoryList.add(category);
    }

    public void removeCategory(CategoryUni category) {
        this.categoryList.remove(category);
    }
}
