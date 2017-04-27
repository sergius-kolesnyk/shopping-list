package com.example.repository;

import com.example.domain.ShoppingListPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingListPermissionRepository extends JpaRepository<ShoppingListPermission, Long> {
    ShoppingListPermission findShoppingListPermissionByIdAndUserId(long id, int userId);
    List<ShoppingListPermission> findShoppingListPermissionsByUserId(int userId);
}
