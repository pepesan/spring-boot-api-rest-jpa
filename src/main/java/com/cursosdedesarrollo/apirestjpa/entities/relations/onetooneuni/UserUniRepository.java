package com.cursosdedesarrollo.apirestjpa.entities.relations.onetooneuni;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserUniRepository extends JpaRepository<User, Long> {
}
