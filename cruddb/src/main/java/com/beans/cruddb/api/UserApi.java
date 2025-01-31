package com.beans.cruddb.api;

import com.beans.cruddb.domain.User;
import com.beans.cruddb.service.UserService;
import com.beans.cruddb.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

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

    @PostMapping("/signup")
    public Optional<User> signup(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/admin")
    public Optional<User> adminUser(@RequestBody User user){
        return userService.saveAdmin(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/{id}")
    private void deleteByUserId(@PathVariable Long id){
        userService.delete(id);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
