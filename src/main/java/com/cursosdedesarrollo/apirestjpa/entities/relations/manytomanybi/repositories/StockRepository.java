package com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanybi.repositories;

import com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanybi.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByStockCode(String stockCode);

}
