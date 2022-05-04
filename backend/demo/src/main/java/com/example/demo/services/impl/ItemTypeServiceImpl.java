package com.example.demo.services.impl;

import com.example.demo.model.dao.IItemTypeRepository;
import com.example.demo.model.entity.Item;
import com.example.demo.model.entity.ItemType;
import com.example.demo.services.IItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemTypeServiceImpl extends AbstractServiceImpl<ItemType, IItemTypeRepository> implements IItemTypeService {
    @Autowired
    protected ItemTypeServiceImpl(IItemTypeRepository defaultDao) {
        super(defaultDao);
    }

    @Override
    public ItemType update(Long id, ItemType entity) {
        findById(id);
        entity.setId(id);
        return create(entity);
    }


}
