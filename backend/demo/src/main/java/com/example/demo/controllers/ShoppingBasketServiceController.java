package com.example.demo.controllers;

import com.example.demo.model.entity.ShoppingBasket;
import com.example.demo.services.IItemService;
import com.example.demo.services.IShoppingBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/shopping_basket")
public class ShoppingBasketServiceController extends AbstractController<ShoppingBasket, IShoppingBasketService> {
    private IItemService iItemService;

    @Autowired
    protected ShoppingBasketServiceController(IShoppingBasketService service, IItemService iItemService) {
        super(service);
        this.iItemService = iItemService;
    }

    private int getTotalPrice(List<ShoppingBasket> purchases) {
        int result = 0;
        for (ShoppingBasket purchase : purchases) {
            result += (iItemService.findById(purchase.getId())).getCost() * purchase.getAmount();
        }
        return result;
    }
}
