package com.qa.ims.persistence.domain;

import java.util.Objects;

public class OrderItem {
    private Long orderId;
    private Long item;
    private int total;

    public OrderItem(Long orderId, Long item) {
        this.orderId = orderId;
        this.item = item;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + orderId +
                ", item=" + item +
                (total > 0?(", total=" + total ):"")+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return total == orderItem.total && Objects.equals(orderId, orderItem.orderId) && Objects.equals(item, orderItem.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, item, total);
    }
}
