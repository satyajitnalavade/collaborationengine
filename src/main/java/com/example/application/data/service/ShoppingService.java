package com.example.application.data.service;

import java.util.List;

import com.example.application.data.entity.ShoppingListItem;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;

@Service
public class ShoppingService {
  private ShoppingListItemRepository shoppingListItemRepo;

  public ShoppingService(
      ShoppingListItemRepository shoppingListItemRepo) {
    this.shoppingListItemRepo = shoppingListItemRepo;
  }

  @Lock(LockModeType.OPTIMISTIC)
  public ShoppingListItem saveItem(ShoppingListItem shoppingListItem) {
    return shoppingListItemRepo.save(shoppingListItem);
  }

  public List<ShoppingListItem> getShoppingList(){
    return shoppingListItemRepo.findAll();
  }

  public void deleteItem(ShoppingListItem item) {
    shoppingListItemRepo.delete(item);
  }
}
