package com.cursosdedesarrollo.apirestjpa.entities.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCacheRepository  extends JpaRepository< BookCache, Long> {
    @Cacheable(cacheNames="bookCache", key="#author")
    public List<BookCache> findByAuthor(String author);
}
