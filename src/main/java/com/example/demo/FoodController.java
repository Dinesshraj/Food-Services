package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class FoodController {

    private final FoodService service;

    public FoodController(FoodService service) {
        this.service = service;
    }

    // GET all orders
    @GetMapping
    public List<FoodOrder> getAllOrders() {
        return service.getAllOrders();
    }

    // GET order by ID
    @GetMapping("/{id}")
    public ResponseEntity<FoodOrder> getOrderById(
            @PathVariable int id) {

        FoodOrder order = service.getOrderById(id);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);
    }

    // POST add order
    @PostMapping
    public FoodOrder addOrder(
            @Valid @RequestBody FoodOrder order) {

        return service.addOrder(order);
    }

    // PUT update entire order
    @PutMapping("/{id}")
    public ResponseEntity<FoodOrder> updateOrder(
            @PathVariable int id,
            @Valid @RequestBody FoodOrder order) {

        FoodOrder updated = service.updateOrder(id, order);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    // PATCH partial update
    @PatchMapping("/{id}")
    public ResponseEntity<FoodOrder> patchOrder(
            @PathVariable int id,
            @RequestBody Map<String, Object> data) {

        FoodOrder updated = service.patchOrder(id, data);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    // DELETE order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
            @PathVariable int id) {

        if (!service.deleteOrder(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}