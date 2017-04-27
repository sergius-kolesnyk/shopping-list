package com.example.repository;

import com.example.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByIdOnDeviceIn(Set<Long> itemListId);
}
