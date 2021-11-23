package com.example.application.data.service;

import com.example.application.data.entity.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Integer> {

}
