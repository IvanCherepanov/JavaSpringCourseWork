package com.example.demo.controllers;

import com.example.demo.model.entity.ItemType;
import com.example.demo.services.IItemTypeService;
import com.example.demo.services.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/item_type")
public class ItemTypeController extends AbstractController<ItemType, IItemTypeService>{
    @Autowired
    protected ItemTypeController(IItemTypeService service) {
        super(service);
    }
}
