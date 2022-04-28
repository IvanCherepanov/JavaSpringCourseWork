package com.example.demo.controllers;

import com.example.demo.model.entity.Pet;
import com.example.demo.services.IPetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/pet")
public class PetController extends AbstractController<Pet, IPetService>{
    protected PetController(IPetService service) {
        super(service);
    }
}
