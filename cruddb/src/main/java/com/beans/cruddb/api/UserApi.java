package com.beans.cruddb.api;

import com.beans.cruddb.domain.User;
import com.beans.cruddb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    private List<User> getALlUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    private User getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found :" + id));
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    private void deleteByUserId(@PathVariable Long id){
        userService.delete(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }
}
