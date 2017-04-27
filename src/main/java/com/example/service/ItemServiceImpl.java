package com.example.service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findByIdOnDeviceIn(Set<Long> itemListId) {
        return itemRepository.findByIdOnDeviceIn(itemListId);
    }

    @Override
    public Item update(long id, Item item) {
        Item one = itemRepository.getOne(id);
        return this.update(one, item);
    }

    @Override
    public Item update(Item original, Item update) {
        original.setIsDeleted(update.getIsDeleted());
        original.setItemName(update.getItemName());
        original.setIsMarkedAsChecked(update.getIsMarkedAsChecked());
        original.setQuantityDescription(update.getQuantityDescription());
        original.setVersion(original.getVersion() + 1);
        return itemRepository.save(original);
    }

    @Override
    public List<Item> save(List<Item> items) {
        return this.itemRepository.save(items);
    }

}
