package com.example.demo.services.impl;

import com.example.demo.model.dao.IItemRepository;
import com.example.demo.model.entity.Item;
import com.example.demo.services.IItemService;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Item> getAllSorting(Integer flag, List<Item> temp) {
        // TODO: 30.04.2022 убрать в отдельный класс
        Sort sort = null;
        if (flag == null) return temp;
        switch (flag) {
            case 0:
                Collections.sort(temp, compareCost);
                break;
            case 1:
                Collections.sort(temp, compareCost);
                Collections.reverse(temp);
                break;
            default:
                return  iItemRepository.findAll();
        }
        return temp;
    }

    private Comparator<Item> compareCost = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getCost()-o2.getCost();
        }
    };

    @Override
    public List<Item> getItemByPetIdAndTypeId(Long pId, Long Iid) {
        return iItemRepository.findItemByPetTypeIdAndItemTypeId(pId, Iid);
    }

    @Override
    public List<Item> getItemByPetId(Long pId) {
        return iItemRepository.findItemByPetTypeId(pId);
    }
}
