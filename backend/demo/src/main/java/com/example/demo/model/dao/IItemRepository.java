package com.example.demo.model.dao;

import com.example.demo.model.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemRepository extends IAbstractRepository<Item>{
    List<Item> findItemByItemName(String itemName);
    List<Item> findItemByItemTypeId(Long itemType);

}
