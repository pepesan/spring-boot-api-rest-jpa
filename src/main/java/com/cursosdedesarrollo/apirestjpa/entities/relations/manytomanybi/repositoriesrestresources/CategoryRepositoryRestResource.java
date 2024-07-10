package com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanybi.repositoriesrestresources;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanybi.domain.Category;

@RepositoryRestResource
public interface CategoryRepositoryRestResource extends CrudRepository<Category, Integer> {

}
