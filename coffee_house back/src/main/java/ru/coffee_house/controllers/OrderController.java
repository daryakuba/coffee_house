package ru.coffee_house.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.coffee_house.models.Order;
import ru.coffee_house.services.OrderService;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping("/orders")
    public void order(@RequestBody Order order){
        service.addOrder(order);
    }

    @GetMapping("/orders")
    public @ResponseBody List<Order> getAll(){
        return service.getOrders();
    }

    @GetMapping("/orders/getOrders/{user_id}")
    public @ResponseBody List<Order> getOrders(@PathVariable long user_id){return service.getUsersOrders(user_id);}

    @GetMapping("/orders/{id}")
    public @ResponseBody Order get(@PathVariable long id){
        return service.getOrder(id);
    }

    @GetMapping("/orders/setBonuses/{user_id}")
    public void setBonuses(@PathVariable long user_id){
        service.setBonuses(user_id);
    }

    @GetMapping("/orders/bonuses/{user_id}")
    public void setAllBonuses(@PathVariable long user_id){
       service.setAllUsersBonuses(user_id);
    }

    @GetMapping("/orders/addProductToOrder/{user_id}/{product_id}")
    public void addProductToOrder(@PathVariable long user_id, @PathVariable long product_id){
        service.addProductToOrder(user_id, product_id);
    }

    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable long id){
        service.deleteOrder(id);
    }
}
