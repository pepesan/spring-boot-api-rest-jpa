package com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanyuni;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/newsuni")
@Slf4j
public class NewsUniController {
    @Autowired
    private NewsUniRepository newsRepository;

    @Autowired
    private CategoryUniRepository categoryRepository;

    @GetMapping("/")
    public List<NewsUni> getAllNews() {
        return newsRepository.findAll();
    }

    @PostMapping("/")
    public NewsUni createNews(@RequestBody NewsUni news) {
        return newsRepository.save(news);
    }

    @PostMapping("/{newsId}/categories/")
    public NewsUni addCategoryToNews(@PathVariable Long newsId, @RequestBody CategoryUni category) {
        NewsUni news = newsRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));
        CategoryUni categoryGuardada = categoryRepository.findByName(category.getName());

        if (categoryGuardada==null){
            log.info("La categoría no existe, vamos a crearla");
            //categoryRepository.save(category); // Save the new category first
        }
        this.categoryRepository.save(category);
        news.addCategory(category);
        return newsRepository.save(news);
    }
}
