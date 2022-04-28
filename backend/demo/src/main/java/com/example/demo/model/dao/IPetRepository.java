package com.example.demo.model.dao;

import com.example.demo.model.entity.Pet;
import org.springframework.stereotype.Repository;

@Repository
public interface IPetRepository extends IAbstractRepository<Pet> {
}
