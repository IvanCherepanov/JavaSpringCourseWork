package com.example.demo.services.impl;

import com.example.demo.model.dao.IItemRepository;
import com.example.demo.model.entity.Item;
import com.example.demo.services.IItemService;

public class ItemServiceImpl extends AbstractServiceImpl<Item, IItemRepository> implements IItemService {
    protected ItemServiceImpl(IItemRepository defaultDao) {
        super(defaultDao);
    }
}
