package com.example.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long idOnDevice;
    private String itemName;
    private boolean isMarkedAsChecked;
    private String quantityDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_list_id")
    @JsonBackReference
    private ShoppingList shoppingList;
    private int version;
    private boolean isDeleted;

    public Item() {
    }

    public Item(long idOnDevice, String itemName, boolean isMarkedAsChecked, String quantityDescription, ShoppingList shoppingList, int version, boolean isDeleted) {
        this.idOnDevice = idOnDevice;
        this.itemName = itemName;
        this.isMarkedAsChecked = isMarkedAsChecked;
        this.quantityDescription = quantityDescription;
        this.shoppingList = shoppingList;
        this.version = version;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean getIsMarkedAsChecked() {
        return isMarkedAsChecked;
    }

    public void setIsMarkedAsChecked(boolean markedAsChecked) {
        isMarkedAsChecked = markedAsChecked;
    }

    public String getQuantityDescription() {
        return quantityDescription;
    }

    public void setQuantityDescription(String quantityDescription) {
        this.quantityDescription = quantityDescription;
    }

    public long getIdOnDevice() {
        return idOnDevice;
    }

    public void setIdOnDevice(long idOnDevice) {
        this.idOnDevice = idOnDevice;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

}
