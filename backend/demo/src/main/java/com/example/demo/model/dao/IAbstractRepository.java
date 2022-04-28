package com.example.demo.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface IAbstractRepository<T> extends JpaRepository<T, Long> {
}
