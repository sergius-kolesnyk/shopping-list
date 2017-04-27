package com.example.service;

import com.example.domain.Item;
import com.example.domain.ShoppingList;
import com.example.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListPermissionService shoppingListPermissionService;
    private final ItemService itemService;

    @Autowired
    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository, ShoppingListPermissionService shoppingListPermissionService, ItemService itemService) {
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListPermissionService = shoppingListPermissionService;
        this.itemService = itemService;
    }

    @Override
    public List<ShoppingList> findByUserId(int userId) {
        return shoppingListPermissionService.getShoppingListByUserId(userId);
    }

    @Override
    public ShoppingList getOne(long id) {
        return shoppingListRepository.getOne(id);
    }

    @Override
    public List<Item> synchronizeList(List<Item> clientItems) {
        List<Item> conflictSet = new ArrayList<>();

        Map<Long, Item> clientUpdatedItemsMap = clientItems
                .stream()
                .collect(Collectors.toMap(Item::getIdOnDevice, Function.identity()));

        List<Item> serverItems = itemService.findByIdOnDeviceIn(clientUpdatedItemsMap.keySet());

        for (Item serverItem : serverItems) {
            Item clientItem = clientUpdatedItemsMap.get(serverItem.getIdOnDevice());
            if(serverItem.getVersion() > clientItem.getVersion()) {
                conflictSet.add(serverItem);
            } else {
                itemService.update(serverItem, clientItem);
            }
        }

        return conflictSet;
    }

    public List<Item> addNewItems(long id, List<Item> clientItems) {
        ShoppingList one = this.getOne(id);
        clientItems.forEach(item -> item.setShoppingList(one));
        return itemService.save(clientItems);
    }
}
