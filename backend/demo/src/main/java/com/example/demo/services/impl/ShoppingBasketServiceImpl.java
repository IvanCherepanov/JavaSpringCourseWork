package com.example.demo.services.impl;

import com.example.demo.model.dao.IShoppingBasketRepository;
import com.example.demo.model.entity.ShoppingBasket;
import com.example.demo.services.IShoppingBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShoppingBasketServiceImpl extends AbstractServiceImpl<ShoppingBasket, IShoppingBasketRepository> implements IShoppingBasketService {
    @Autowired
    protected ShoppingBasketServiceImpl(IShoppingBasketRepository defaultDao) {
        super(defaultDao);
    }

    @Override
    public ShoppingBasket update(Long id, ShoppingBasket entity) {
        findById(id);
        entity.setId(id);
        return create(entity);
    }
}
