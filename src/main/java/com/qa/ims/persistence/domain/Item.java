package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {
    private Long id;
    private String itemName;
    private Long itemQuantity;
    private Long itemPrice;

    public Item(String itemName, Long itemQuantity, Long itemPrice) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }

    public Item(Long id, String itemName, Long itemQuantity, Long itemPrice) {
        this.id = id;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", itemPrice=" + itemPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(itemName, item.itemName) && Objects.equals(itemQuantity, item.itemQuantity) && Objects.equals(itemPrice, item.itemPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, itemQuantity, itemPrice);
    }
}
