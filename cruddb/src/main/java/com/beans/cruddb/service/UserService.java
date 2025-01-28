package com.beans.cruddb.service;

import com.beans.cruddb.domain.User;
import com.beans.cruddb.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public User createUser(User user){
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User userDetails){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("USer Not Found Excetion :" + id));

        user = userRepository.getOne(id);

        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());

        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
