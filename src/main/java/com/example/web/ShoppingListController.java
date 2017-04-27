package com.example.web;

import com.example.domain.ShoppingList;
import com.example.domain.SynchronizeRequest;
import com.example.security.AuthenticationToken;
import com.example.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingListController {
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListController(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    @PostMapping("/list")
    public ResponseEntity<?> addList() {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setListName("custom");
        shoppingList.setUserId(1);
        ShoppingList save = shoppingListRepository.save(shoppingList);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @RequestMapping("/list")
    public List<ShoppingList> getLists(@RequestParam(value="userId", defaultValue="1") int userId) {
        return shoppingListRepository.findByUserId(userId);
    }

    @RequestMapping(value = "/synchronize-list/{id}", method = RequestMethod.POST)
    public ResponseEntity<SynchronizeRequest> synchronizeList(@PathVariable int id, @RequestBody SynchronizeRequest synchronizeRequest) {
        AuthenticationToken auth = (AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthenticatedUser().getUsername());
        System.out.println(id);
        return new ResponseEntity<>(synchronizeRequest, HttpStatus.OK);
    }
}
