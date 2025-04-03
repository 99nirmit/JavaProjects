package com.ecommerceapp.ecommerce.services;

import com.ecommerceapp.ecommerce.models.User;
import com.ecommerceapp.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    public User updateUser(User updatedUer){
        User existingUser = new User();
        existingUser.setName(updatedUer.getName());
        existingUser.setEmail(updatedUer.getEmail());
        existingUser.setPassword(updatedUer.getPassword());
        existingUser.setRoles(updatedUer.getRoles());
        existingUser.setPhoneNumber(updatedUer.getPhoneNumber());
        existingUser.setAddress(updatedUer.getAddress());
        existingUser.setStatus(updatedUer.getStatus());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
