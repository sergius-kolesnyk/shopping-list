package com.example.service;


import com.example.domain.ShoppingList;
import com.example.domain.ShoppingListPermission;

import java.util.List;

public interface ShoppingListPermissionService {
    boolean isUserHasPermission(long listId, int userId);
    ShoppingListPermission savePermission(ShoppingListPermission shoppingListPermission);
    List<ShoppingList> getShoppingListByUserId(int userId);
}
