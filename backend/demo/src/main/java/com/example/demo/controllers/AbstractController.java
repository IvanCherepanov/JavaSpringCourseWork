package com.example.demo.controllers;

import com.example.demo.services.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractController<T, S extends IAbstractService<T>>{
    private final S service;

    protected AbstractController(S service) {
        this.service = service;
    }

    @PostMapping(value="")
    public T create(@RequestBody T entity) {
        return service.create(entity);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable(name="id") Long id, @RequestBody T entity) {
        return service.update(id,entity);
    }

    @GetMapping("/{id}")
    public T read(@PathVariable(name="id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") Long id) {
        service.delete(id);
    }
}
