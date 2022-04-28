package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

// TODO: 28.04.2022 удалить
public interface IAbstractController<T> {
    @PostMapping(value="")
    T create(@RequestBody T entity);

    @PutMapping("/{id}")
    T update(@RequestParam(name="id") Long id, @RequestBody T entity);

    @GetMapping("/{id}")
    T read(@RequestParam(name="id") Long id);

    @DeleteMapping("/{id}")
    void delete(@RequestParam(name="id") Long id) ;

}
