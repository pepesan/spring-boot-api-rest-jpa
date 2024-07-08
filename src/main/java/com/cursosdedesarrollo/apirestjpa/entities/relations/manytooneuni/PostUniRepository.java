package com.cursosdedesarrollo.apirestjpa.entities.relations.manytooneuni;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostUniRepository extends JpaRepository<PostUni, Long> {
}
