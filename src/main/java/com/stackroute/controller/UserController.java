package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/val")
public class UserController
{
    UserService userService;

    public UserController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody User user)
    {
        ResponseEntity responseEntity;
        try {
            userService.saveUser(user);
            responseEntity=new ResponseEntity<String>("Successfully Creaed..!!!", HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsException e)
        {
            responseEntity=new ResponseEntity<String >(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers()
    {
        return new ResponseEntity<List<User>>(userService.getAllUser(),HttpStatus.OK );
    }
}
