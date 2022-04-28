package com.example.demo.services.impl;

import com.example.demo.model.dao.IItemTypeRepository;
import com.example.demo.model.entity.ItemType;
import com.example.demo.services.IItemTypeService;

public class ItemTypeServiceImpl extends AbstractServiceImpl<ItemType, IItemTypeRepository> implements IItemTypeService {
    protected ItemTypeServiceImpl(IItemTypeRepository defaultDao) {
        super(defaultDao);
    }
}
