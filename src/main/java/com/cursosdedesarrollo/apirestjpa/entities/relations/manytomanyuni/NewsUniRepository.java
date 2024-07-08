package com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanyuni;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsUniRepository extends JpaRepository<NewsUni, Long> {
}
