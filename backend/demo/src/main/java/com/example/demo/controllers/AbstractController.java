package com.example.demo.controllers;

import com.example.demo.services.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class AbstractController<T, S extends IAbstractService<T>> {
    protected final S service;

    protected AbstractController(S service) {
        this.service = service;
    }

    @PostMapping(value = "")
    public T create(@RequestBody T entity) {
        return service.create(entity);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable(name = "id") Long id, @RequestBody T entity) {
        return service.update(id, entity);
    }

    @GetMapping("/{id}")
    public T read(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping("")
    public List<T> readAll() {
        return service.getAll();
    }

    // TODO: 30.04.2022 пересмотреть подход к поиску и его применению 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        if (service.findById(id) != Optional.empty()) {
            System.out.println(service.findById(id));
            service.delete(id);
        }

    }
}
