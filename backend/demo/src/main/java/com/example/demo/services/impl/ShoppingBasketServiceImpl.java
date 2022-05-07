package com.example.demo.services.impl;

import com.example.demo.model.dao.IShoppingBasketRepository;
import com.example.demo.model.entity.ShoppingBasket;
import com.example.demo.services.IShoppingBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShoppingBasketServiceImpl extends AbstractServiceImpl<ShoppingBasket, IShoppingBasketRepository> implements IShoppingBasketService {
    private IShoppingBasketRepository iShoppingBasketRepository;
    @Autowired
    protected ShoppingBasketServiceImpl(IShoppingBasketRepository defaultDao) {
        super(defaultDao);
        this.iShoppingBasketRepository = defaultDao;
    }

    @Override
    public ShoppingBasket update(Long id, ShoppingBasket entity) {
        findById(id);
        entity.setId(id);
        return create(entity);
    }

    @Override
    public ShoppingBasket getShoppingBasketByUserIdAndItemId(Long userId, Long itemId) {
        return iShoppingBasketRepository.findShoppingBasketByUserIdAndItemId(userId, itemId);
    }

    @Override
    public ShoppingBasket getShoppingBasketByIdAndUserId(Long addId, Long userId) {
        return iShoppingBasketRepository.findShoppingBasketByIdAndUserId(addId, userId);
    }

    @Override
    public List<ShoppingBasket> getItemByUserId(Long userId) {
        return iShoppingBasketRepository.findAllByUserId(userId);
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        iShoppingBasketRepository.deleteAllByUserId(userId);
    }
}
