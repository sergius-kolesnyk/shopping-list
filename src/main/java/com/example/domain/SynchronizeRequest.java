package com.example.domain;

import java.util.ArrayList;
import java.util.List;

public class SynchronizeRequest {
    private List<Item> created = new ArrayList<>();
    private List<Item> updated = new ArrayList<>();

    public SynchronizeRequest() {
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
}
