package com.example.demo.model.dao;

import com.example.demo.model.entity.ShoppingBasket;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShoppingBasketRepository extends IAbstractRepository<ShoppingBasket> {
    ShoppingBasket findShoppingBasketByUserIdAndItemId(Long userId, Long itemId);
    ShoppingBasket findShoppingBasketByIdAndUserId(Long addId, Long userId);
    List<ShoppingBasket> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
