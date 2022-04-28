package com.example.demo.services.impl;

import com.example.demo.model.dao.IPetRepository;
import com.example.demo.model.entity.Pet;
import com.example.demo.services.IPetService;

public class PetServiceImpl extends AbstractServiceImpl<Pet, IPetRepository> implements IPetService {
    protected PetServiceImpl(IPetRepository defaultDao) {
        super(defaultDao);
    }
}
