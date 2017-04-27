package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_list")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long idOnDevice;
    private String listName;
    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Item> items;
    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShoppingListPermission> shoppingListPermissions;

    public ShoppingList() {
    }

    public ShoppingList(long idOnDevice, String listName, List<Item> items) {
        this.idOnDevice = idOnDevice;
        this.listName = listName;
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public long getIdOnDevice() {
        return idOnDevice;
    }

    public void setIdOnDevice(long idOnDevice) {
        this.idOnDevice = idOnDevice;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
