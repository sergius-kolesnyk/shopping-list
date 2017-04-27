package com.example.web;

import com.example.domain.Item;
import com.example.domain.ShoppingList;
import com.example.domain.SynchronizeListRequest;
import com.example.domain.SynchronizeResponse;
import com.example.security.AuthenticationInfo;
import com.example.service.ItemService;
import com.example.service.ShoppingListPermissionService;
import com.example.service.ShoppingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShoppingListController {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingListController.class);
    private final ShoppingListService shoppingListService;
    private final ShoppingListPermissionService shoppingListPermissionService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService, ShoppingListPermissionService shoppingListPermissionService) {
        this.shoppingListService = shoppingListService;
        this.shoppingListPermissionService = shoppingListPermissionService;
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getList(@PathVariable int id) {
        ShoppingList one = shoppingListService.getOne(id);
        return new ResponseEntity<>(one, HttpStatus.OK);
    }

    @RequestMapping("/list")
    public ResponseEntity<?> getLists() {
        AuthenticationInfo authentication = (AuthenticationInfo) SecurityContextHolder.getContext().getAuthentication();
        List<ShoppingList> shoppingLists = shoppingListService.findByUserId(authentication.getDetails().getId());

        return new ResponseEntity<Object>(shoppingLists, HttpStatus.OK);
    }

    @RequestMapping(value = "/synchronize-list/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> synchronizeList(@PathVariable long id, @RequestBody SynchronizeListRequest synchronizeListRequest) {
        AuthenticationInfo authentication = (AuthenticationInfo) SecurityContextHolder.getContext().getAuthentication();

        boolean userHasPermission = shoppingListPermissionService.isUserHasPermission(id, authentication.getDetails().getId());
        if(!userHasPermission) {
            logger.error("User #" + authentication.getDetails().getId() + " trying to synchronize list #" + id);
            return new ResponseEntity<>("You don't have permission to synchronize this list", HttpStatus.UNAUTHORIZED);
        }

        shoppingListService.addNewItems(id, synchronizeListRequest.getCreated());
        List<Item> conflictSet = new ArrayList<>();
        List<Item> updatedConflictItems = shoppingListService.synchronizeList(synchronizeListRequest.getUpdated());
        conflictSet.addAll(updatedConflictItems);
        List<Item> deletedConflictItems = shoppingListService.synchronizeList(synchronizeListRequest.getDeleted());
        conflictSet.addAll(deletedConflictItems);


        List<Item> items = shoppingListService.getOne(id).getItems();

        items.removeAll(conflictSet);

        return new ResponseEntity<>(new SynchronizeResponse(conflictSet, items), HttpStatus.OK);
    }
}
