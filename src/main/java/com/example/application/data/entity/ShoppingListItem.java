package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.example.application.data.AbstractEntity;

@Entity
public class ShoppingListItem extends AbstractEntity {

  @Version
  private Long version;
  private int amount;
  private String name;
  private String category;

  public Long getVersion() {
    return version;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

}
