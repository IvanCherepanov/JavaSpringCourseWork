package com.example.demo.services.impl;

import com.example.demo.model.dao.IItemRepository;
import com.example.demo.model.entity.Item;
import com.example.demo.services.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceImpl extends AbstractServiceImpl<Item, IItemRepository> implements IItemService {
    @Autowired
    protected ItemServiceImpl(IItemRepository defaultDao) {
        super(defaultDao);
    }

    @Override
    public Item update(Long id, Item entity) {
        findById(id);
        entity.setId(id);
        return create(entity);
    }
}
