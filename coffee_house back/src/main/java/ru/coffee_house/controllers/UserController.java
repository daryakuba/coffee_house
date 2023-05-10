package ru.coffee_house.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.coffee_house.models.User;
import ru.coffee_house.services.UserService;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public void post(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("/users")
    public @ResponseBody
    List<User> getAll() {
        return userService.getUsers();
    }

    @GetMapping("/users/getIdByLogin/{login}")
    public Long getIdByLong(@PathVariable String login) {
        return userService.getIdByLogin(login);
    }

    @GetMapping("/users/{id}")
    public @ResponseBody
    User get(@PathVariable long id) {
        return userService.getUser(id);
    }

    @GetMapping("/users/getBonuses/{user_id}")
    public int getBonuses(@PathVariable long user_id) {
        return userService.getBonusesPerUser(user_id);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
