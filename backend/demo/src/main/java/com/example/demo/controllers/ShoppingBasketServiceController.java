package com.example.demo.controllers;

import com.example.demo.model.entity.ShoppingBasket;
import com.example.demo.services.IShoppingBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/shopping_basket")
public class ShoppingBasketServiceController extends AbstractController<ShoppingBasket, IShoppingBasketService>{
    @Autowired
    protected ShoppingBasketServiceController(IShoppingBasketService service) {
        super(service);
    }
}
