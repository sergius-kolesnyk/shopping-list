package com.example.domain;

import java.util.ArrayList;
import java.util.List;

public class SynchronizeListRequest {
    private long idOnDevice;
    private String listName;
    private List<Item> created = new ArrayList<>();
    private List<Item> updated = new ArrayList<>();
    private List<Item> deleted = new ArrayList<>();

    public SynchronizeListRequest() {
    }

    public long getIdOnDevice() {
        return idOnDevice;
    }

    public void setIdOnDevice(long idOnDevice) {
        this.idOnDevice = idOnDevice;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<Item> getCreated() {
        return created;
    }

    public void setCreated(List<Item> created) {
        this.created = created;
    }

    public List<Item> getUpdated() {
        return updated;
    }

    public void setUpdated(List<Item> updated) {
        this.updated = updated;
    }

    public List<Item> getDeleted() {
        return deleted;
    }

    public void setDeleted(List<Item> deleted) {
        this.deleted = deleted;
    }
}
