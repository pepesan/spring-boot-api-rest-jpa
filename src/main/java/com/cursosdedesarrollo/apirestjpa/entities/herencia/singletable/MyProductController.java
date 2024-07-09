package com.cursosdedesarrollo.apirestjpa.entities.herencia.singletable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/myproductssingle")
public class MyProductController {
    @Autowired
    private MyProductRepository myProductRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PenRepository penRepository;

    @GetMapping("/")
    public List<MyProduct> index(){
        return this.myProductRepository.findAll();
    }

    @GetMapping("/book/")
    public List<Book> indexBook(){
        return this.bookRepository.findAll();
    }

    @GetMapping("/pen/")
    public List<Pen> indexPen(){
        return this.penRepository.findAll();
    }

    @GetMapping("/insert/")
    public List<MyProduct> insert(){
        Book book = new Book("1984", "George Orwell");
        this.bookRepository.save(book);
        Pen pen = new Pen("my pen", "blue");
        this.penRepository.save(pen);
        return this.myProductRepository.findAll();
    }
}
