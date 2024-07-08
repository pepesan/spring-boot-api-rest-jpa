package com.cursosdedesarrollo.apirestjpa.entities.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/bookscache")
public class BookCacheController {

    @Autowired
    private BookCacheRepository bookRepository;

    @GetMapping("/")
    public List<BookCache> index(){
        return this.bookRepository.findAll();
    }

    @PostMapping("/")
    public BookCache add(@RequestBody BookCache bookCache){
        this.bookRepository.save(bookCache);
        return bookCache;
    }

    @GetMapping("/{id}")
    @Transactional
    // cacheable
    @Cacheable(
            value = "getBookCache",
            key = "#id",
            condition = "#id>0")
    public BookCache getBook(@PathVariable Long id) {
        // Primera consulta
        BookCache book1 = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Segunda consulta, debería devolver el mismo objeto desde el cache de nivel 1

        BookCache book2 = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Verifica si ambos objetos son iguales (debería ser true)
        System.out.println("Same instance: " + (book1 == book2));

        return book1;
    }
}

