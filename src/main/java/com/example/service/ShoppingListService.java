package com.example.service;

import com.example.domain.Item;
import com.example.domain.ShoppingList;

import java.util.List;

public interface ShoppingListService {
    List<ShoppingList> findByUserId(int userId);
    ShoppingList getOne(long id);
    List<Item> synchronizeList(List<Item> items);
    List<Item> addNewItems(long id, List<Item> clientItems);
}
