package com.example.demo.services;

import com.example.demo.model.entity.ShoppingBasket;

import java.util.List;

public interface IShoppingBasketService extends IAbstractService<ShoppingBasket> {
    ShoppingBasket getShoppingBasketByUserIdAndItemId(Long userId, Long ItemId);
    ShoppingBasket getShoppingBasketByIdAndUserId(Long addId, Long userId);
    List<ShoppingBasket> getItemByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
