package com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanyuni;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryUniRepository extends JpaRepository<CategoryUni, Long> {
    public CategoryUni findByName(String name);
}
