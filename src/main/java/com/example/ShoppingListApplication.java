package com.example;

import com.example.domain.Item;
import com.example.domain.ShoppingList;
import com.example.domain.ShoppingListPermission;
import com.example.repository.ShoppingListRepository;
import com.example.service.ShoppingListPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ShoppingListApplication implements CommandLineRunner {

    @Autowired
    private ShoppingListRepository shoppingListRepository;
    @Autowired
    private ShoppingListPermissionService shoppingListPermissionService;

	public static void main(String[] args) {
		SpringApplication.run(ShoppingListApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		ShoppingList shoppingList = new ShoppingList(1, "first list", Collections.emptyList());
		List<Item> items = new ArrayList<Item>(){{
		    add(new Item(1, "milk", false, "2", shoppingList, 1, false));
		    add(new Item(2, "bread", false, "1", shoppingList, 1, false));
        }};
		shoppingList.setItems(items);

        ShoppingList save = shoppingListRepository.save(shoppingList);

        shoppingListPermissionService.savePermission(new ShoppingListPermission(save, 1));
	}
}
