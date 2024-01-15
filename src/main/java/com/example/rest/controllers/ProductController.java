package com.example.rest.controllers;

import com.example.domain.entity.Product;
import com.example.domain.repo.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProductController {

    private ProductRepository repo;

    public ProductController(ProductRepository repo){
        this.repo = repo;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@RequestBody @Valid Product product) {
        return repo.save(product);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@PathVariable("id") Integer id, @RequestBody @Valid Product product) {
        repo.findById(id)
                .map(p -> {
                    product.setId(p.getId());
                    repo.save(product);
                    return product;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        repo.findById(id)
                .map(p -> {
                    repo.delete(p);
                    return Void.TYPE;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable("id") Integer id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @GetMapping
    public List<Product> find(Product filter) throws Exception {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Product> example = Example.of(filter, matcher);
        List<Product> resultList = repo.findAll(example);
        if (resultList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Product not found");
        }
        return resultList;
    }

}
