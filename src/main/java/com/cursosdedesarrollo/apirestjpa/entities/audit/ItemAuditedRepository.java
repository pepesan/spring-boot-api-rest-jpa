package com.cursosdedesarrollo.apirestjpa.entities.audit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemAuditedRepository extends JpaRepository<ItemAudited, Long>, RevisionRepository<ItemAudited, Long, Integer> {
}
