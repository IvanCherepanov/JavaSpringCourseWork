package com.example.demo.services.impl;

import com.example.demo.model.dao.IShoppingBasketRepository;
import com.example.demo.model.entity.ShoppingBasket;
import com.example.demo.services.IShoppingBasketService;

public class ShoppingBasketServiceImpl extends AbstractServiceImpl<ShoppingBasket, IShoppingBasketRepository> implements IShoppingBasketService {
    protected ShoppingBasketServiceImpl(IShoppingBasketRepository defaultDao) {
        super(defaultDao);
    }
}
