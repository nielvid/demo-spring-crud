package com.example.demo.controllers;
import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.exception.UserValidationException;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

private final UserService userService;
private final AtomicLong counter = new AtomicLong();

@Autowired
public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(  @Valid @NonNull @RequestBody User user) throws UserValidationException {
        userService.createUser(user);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "limit", defaultValue = "50") int limit){
        return userService.getUsers();
    }

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable UUID id) {
        return userService.findUserById(id).orElse(null);
    }
//    @PostMapping(
//            produces  = {
//                    MediaType.APPLICATION_ATOM_XML_VALUE,
//                    MediaType.APPLICATION_ATOM_XML_VALUE
//            }
//    )
//    public User createUser() {
//        User user =  new User();
//        user.setId(counter.incrementAndGet());
//        user.setName("John Doe");
//        System.out.println(user.toString());
//        return user;
//    }
    @PatchMapping(path="/{id}")
    public int updateUser(@PathVariable UUID id,  @RequestBody User user) {
        return userService.updateUser(id, user);
    }
    @DeleteMapping(path = "/{id}")
    public int deleteUser(@PathVariable UUID id) {
        return userService.deleteUserById(id);
    }
}
