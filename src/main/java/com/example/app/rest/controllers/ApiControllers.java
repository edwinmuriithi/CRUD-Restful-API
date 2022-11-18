package com.example.app.rest.controllers;

import com.example.app.rest.models.User;
import com.example.app.rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepository userRepository;
    @GetMapping(value = "/")
    public String getPage(){
        return "Welcome";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/save")
    public String saveUser (@RequestBody User user){
        userRepository.save(user);
        return "Saved user!";
    }

    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = userRepository.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setOccupation(user.getOccupation());
        updatedUser.setAge(user.getAge());
        userRepository.save(updatedUser);
        return "Updated user";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deletedUser = userRepository.findById(id).get();
        userRepository.delete(deletedUser);
        return  "Deleted the user in question"+id;
    }
}
