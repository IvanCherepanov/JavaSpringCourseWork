package com.example.demo.services;

import com.example.demo.model.entity.Item;

import java.util.List;

public interface IItemService extends IAbstractService<Item> {
    List<Item> getItemByName(String name);
    List<Item> getItemByItemType(Long itemType);
    List<Item> getAllSorting(Integer flag,List<Item> temp);
    List<Item> getItemByPetIdAndTypeId(Long pId, Long Iid);
    List<Item> getItemByPetId(Long pId);
    List<Item> getItemContainingItemName(String name);
}
