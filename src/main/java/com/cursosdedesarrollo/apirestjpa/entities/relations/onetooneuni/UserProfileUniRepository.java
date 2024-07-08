package com.cursosdedesarrollo.apirestjpa.entities.relations.onetooneuni;

import com.cursosdedesarrollo.apirestjpa.entities.relations.onetoonebi.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileUniRepository extends JpaRepository<UserProfile, Long> {
}
