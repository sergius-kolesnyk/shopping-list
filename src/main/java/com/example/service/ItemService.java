package com.example.service;


import com.example.domain.Item;

import java.util.List;
import java.util.Set;

public interface ItemService {
    List<Item> findByIdOnDeviceIn(Set<Long> itemListId);
    Item update(long id, Item item);
    Item update(Item original, Item update);
    List<Item> save(List<Item> items);
}
