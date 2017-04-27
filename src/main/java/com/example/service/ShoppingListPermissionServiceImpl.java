package com.example.service;

import com.example.domain.ShoppingList;
import com.example.domain.ShoppingListPermission;
import com.example.repository.ShoppingListPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingListPermissionServiceImpl implements ShoppingListPermissionService {
    private final ShoppingListPermissionRepository shoppingListPermissionRepository;

    @Autowired
    public ShoppingListPermissionServiceImpl(ShoppingListPermissionRepository shoppingListPermissionRepository) {
        this.shoppingListPermissionRepository = shoppingListPermissionRepository;
    }

    @Override
    public boolean isUserHasPermission(long listId, int userId) {
        ShoppingListPermission shoppingListPermissionByIdAndUserId =
                shoppingListPermissionRepository.findShoppingListPermissionByIdAndUserId(listId, userId);
        return shoppingListPermissionByIdAndUserId != null;
    }

    @Override
    public ShoppingListPermission savePermission(ShoppingListPermission shoppingListPermission) {
        return shoppingListPermissionRepository.save(shoppingListPermission);
    }

    @Override
    public List<ShoppingList> getShoppingListByUserId(int userId) {
        return this.shoppingListPermissionRepository.findShoppingListPermissionsByUserId(userId)
                .stream()
                .map(ShoppingListPermission::getShoppingList)
                .collect(Collectors.toList());
    }

}
