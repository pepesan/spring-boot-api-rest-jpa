package com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanybi;

import com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanybi.domain.Category;
import com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanybi.domain.Stock;
import com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanybi.repositories.CategoryRepository;
import com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanybi.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/stonks")
public class StockController {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private
    CategoryRepository categoryRepository;

    @GetMapping("/")
    public List<Stock> index(){
        return this.stockRepository.findAll();
    }

    @PostMapping("/")
    public Stock add(
            @RequestBody Stock stock
    ){
        this.stockRepository.save(stock);
        return stock;
    }

    @GetMapping("/{id}")
    public Stock getById(
            @PathVariable("id") Long id
    ){
        return this.stockRepository
                .findById(id)
                .orElse(new Stock());
    }

    @PostMapping("/{id}/category/")
    public Stock addCategoryById(
            @PathVariable("id") Long id,
            @RequestBody Category category
    ){
        Optional< Stock> posibleStock = this.stockRepository
                .findById(id);
        if (posibleStock.isPresent()){
            Stock actualStock = posibleStock.get();
            Optional<Category> posibleCategoria = this.categoryRepository
                    .findByName(category.getName())
                    .stream().findFirst();
            Category actualCategory;
            actualCategory = posibleCategoria.orElseGet(() -> category);
            actualStock.getCategories().add(actualCategory);
            this.stockRepository.save(actualStock);
            //actualCategory.getStocks().add(actualStock);
            //this.categoryRepository.save(actualCategory);
            return actualStock;
        }
        return new Stock();

    }
    // TODO: Revisar porque no hat relación en stock_category
    @PostMapping("/{id}/stock/")
    public Category addStockById(
            @PathVariable("id") Long id,
            @RequestBody Stock stock
    ){
        Optional< Category> posibleCategory = this.categoryRepository
                .findById(id);
        if (posibleCategory.isPresent()){
            Category actualCategory = posibleCategory.get();
            Optional<Stock> posibleStock = this.stockRepository
                    .findByStockCode(stock.getStockCode())
                    .stream().findFirst();
            Stock actualStock;
            actualStock = posibleStock.orElseGet(() -> stock);
            actualCategory.getStocks().add(actualStock);
            this.categoryRepository.save(actualCategory);
            //actualCategory.getStocks().add(actualStock);
            //this.categoryRepository.save(actualCategory);
            return actualCategory;
        }
        return new Category();
    }
}
