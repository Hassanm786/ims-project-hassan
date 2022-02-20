package com.qa.ims.persistence.domain;


import java.util.List;
import java.util.Objects;

public class Order {
    private Long id;
    private Long customerId;
    private List<OrderItem> orderItemList;

    public Order(Long customerId) {
        this.customerId = customerId;
    }

    public Order(Long id, Long customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public Order(Long customerId, List<OrderItem> orderItemList) {
        this.customerId = customerId;
        this.orderItemList = orderItemList;
    }

    public Order(Long id, Long customerId, List<OrderItem> orderItemList) {
        this.id = id;
        this.customerId = customerId;
        this.orderItemList = orderItemList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderItemList=" + orderItemList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(customerId, order.customerId) && Objects.equals(orderItemList, order.orderItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, orderItemList);
    }
}
