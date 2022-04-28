package com.example.demo.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAbstractRepository<T> extends JpaRepository<T, Long> {
}
