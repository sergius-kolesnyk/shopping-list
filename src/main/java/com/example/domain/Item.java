package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long idOnDevice;
    private String itemName;
    private boolean isMarkedAsChecked;
    private String quantityDescription;

    public Item() {
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

    public boolean isMarkedAsCheked() {
        return isMarkedAsChecked;
    }

    public void setMarkedAsChecked(boolean markedAsChecked) {
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
}
