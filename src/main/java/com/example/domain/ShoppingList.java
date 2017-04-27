package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long idOnDevice;
    private String listName;
    private int userId;

    public ShoppingList() {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getIdOnDevice() {
        return idOnDevice;
    }

    public void setIdOnDevice(long idOnDevice) {
        this.idOnDevice = idOnDevice;
    }
}
