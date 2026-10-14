package com.example.demo;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class FoodService {

    private List<FoodOrder> orders = new ArrayList<>();
    private int nextId = 1;

    public List<FoodOrder> getAllOrders() {
        return orders;
    }

    public FoodOrder getOrderById(int id) {
        return orders.stream()
                .filter(o -> o.getOrderId() == id)
                .findFirst()
                .orElse(null);
    }

    public FoodOrder addOrder(FoodOrder order) {
        order.setOrderId(nextId++);
        orders.add(order);
        return order;
    }

    public FoodOrder updateOrder(int id, FoodOrder newOrder) {
        FoodOrder order = getOrderById(id);

        if (order != null) {
            order.setCustomerName(newOrder.getCustomerName());
            order.setFoodItems(newOrder.getFoodItems());
            order.setQuantity(newOrder.getQuantity());
            order.setPrice(newOrder.getPrice());
        }

        return order;
    }

    public FoodOrder patchOrder(int id, Map<String, Object> data) {
        FoodOrder order = getOrderById(id);

        if (order != null) {
            if (data.containsKey("customerName"))
                order.setCustomerName(
                    (String) data.get("customerName"));

            if (data.containsKey("foodItems"))
                order.setFoodItems(
                    (String) data.get("foodItems"));

            if (data.containsKey("quantity"))
                order.setQuantity(
                    ((Number) data.get("quantity")).intValue());

            if (data.containsKey("price"))
                order.setPrice(
                    ((Number) data.get("price")).doubleValue());
        }

        return order;
    }

    public boolean deleteOrder(int id) {
        return orders.removeIf(
                order -> order.getOrderId() == id);
    }
}