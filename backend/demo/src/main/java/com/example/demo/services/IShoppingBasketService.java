package com.example.demo.services;

import com.example.demo.model.entity.ShoppingBasket;
import com.example.demo.model.entity.User;

import java.util.List;

public interface IShoppingBasketService extends IAbstractService<ShoppingBasket> {
    ShoppingBasket getShoppingBasketByUserIdAndItemId(Long userId, Long ItemId);
    ShoppingBasket getShoppingBasketByIdAndUserId(Long addId, Long userId);
    List<ShoppingBasket> getItemByUserId(Long userId);
    void deleteAllByUserId(Long userId);
    int getTotalPrice(List<ShoppingBasket> purchases);
    String createMessageForUser(List<ShoppingBasket> userPurchases, Long userId);
    String createMessageForManager(User user, String address, String telephone, List<ShoppingBasket> userPurchases);
}
