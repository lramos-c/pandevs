package org.generation.pandevs.controller;

import org.generation.pandevs.DTO.OrderDTO;
import org.generation.pandevs.model.OrderEntity;
import org.generation.pandevs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderEntity> getAll(){
        return this.orderService.getOrders();
    }

    //Metodo para mapear una nueva order
    @PostMapping("/create")
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.orderService.createOrder(dto));
    }

}
