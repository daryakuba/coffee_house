package ru.coffee_house.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.coffee_house.models.table;
import ru.coffee_house.services.TableService;

@RestController
public class TableController {
    @Autowired
    private TableService service;

    @GetMapping("/tables/rentStatus/{table_id}")
    public void updStatus(@PathVariable long table_id){
        service.rentStatus(table_id);
    }

    @GetMapping("/tables/freeStatus/{table_id}")
    public void updFreeStatus(@PathVariable long table_id){
        service.freeStatus(table_id);
    }

    @PostMapping("/tables")
    public void table(@RequestBody table table){
        service.addTable(table);
    }

    @GetMapping("/tables")
    public @ResponseBody
    List<table> getAll(){
        return service.getTables();
    }

    @GetMapping("/tables/rent")
    public @ResponseBody List<table> getAllRent(){return service.getRentedTables();}

    @GetMapping("/tables/exist")
    public String isFreeExist(){
        return service.isFreeTableExist();
    }

    @GetMapping("/tables/getFree")
    public long getFree(){
        return service.getFreeTable().getId();
    }

    @DeleteMapping("/tables/{id}")
    public void delete(@PathVariable long id){
        service.deleteTable(id);
    }

    @GetMapping("/tables/usersTable/{user_id}")
    public long getUtable(@PathVariable long user_id){
        return service.getUsersTable(user_id);
    }

    @GetMapping("/tables/getNumberOfT/{user_id}")
    public int getNumTable(@PathVariable long user_id){
        return service.getNumOfTable(user_id);
    }

    @GetMapping("/tables/addUser/{user_id}/{table_id}")
    public void addUser(@PathVariable long user_id, @PathVariable long table_id){
        service.addUser(table_id, user_id);
    }

    @GetMapping("/tables/delUser/{table_id}")
    public void deleteUser(@PathVariable long table_id){
        service.updateUser(table_id);
    }
}
