package com.example.demo.controllers;

import com.example.demo.model.entity.Item;
import com.example.demo.services.IItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/item")
public class ItemController extends AbstractController<Item, IItemService> {
    protected ItemController(IItemService service) {
        super(service);
    }
}
