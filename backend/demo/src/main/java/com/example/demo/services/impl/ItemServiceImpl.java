package com.example.demo.services.impl;

import com.example.demo.model.dao.IItemRepository;
import com.example.demo.model.entity.Item;
import com.example.demo.services.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl extends AbstractServiceImpl<Item, IItemRepository> implements IItemService {
    private IItemRepository iItemRepository;

    @Autowired
    public ItemServiceImpl(IItemRepository iItemRepository) {
        super(iItemRepository);
        this.iItemRepository = iItemRepository;
    }

    @Override
    public Item update(Long id, Item entity) {
        findById(id);
        entity.setId(id);
        return create(entity);
    }

    @Override
    public List<Item> getItemByName(String name) {
        return iItemRepository.findItemByItemName(name);
    }

    @Override
    public List<Item> getItemByItemType(Long itemType) {
        return iItemRepository.findItemByItemTypeId(itemType);
    }

    @Override
    public List<Item> getAllSorting(int flag) {
        // TODO: 30.04.2022 убрать в отдельный класс
        Sort sort = null;
        if (flag == 1) {
            sort = Sort.by(
                    Sort.Order.asc("cost"));
        }
        return iItemRepository.findAll(sort);
    }

    @Override
    public List<Item> getItemByPetIdAndTypeId(Long pId, Long Iid) {
        return iItemRepository.findItemByPetTypeIdAndItemTypeId(pId, Iid);
    }

    @Override
    public List<Item> getItemByPetId(Long pId) {
        return iItemRepository.findItemByPetTypeId(pId);
    }

}
