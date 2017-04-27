package com.example.domain;

import java.util.ArrayList;
import java.util.List;

public class SynchronizeResponse {
    private List<Item> conflicts = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public SynchronizeResponse() {
    }

    public SynchronizeResponse(List<Item> conflicts, List<Item> items) {
        this.conflicts = conflicts;
        this.items = items;
    }

    public List<Item> getConflicts() {
        return conflicts;
    }

    public void setConflicts(List<Item> conflicts) {
        this.conflicts = conflicts;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
