package com.beans.cruddb.service;
import com.beans.cruddb.Enum.Role;
import com.beans.cruddb.domain.User;
import com.beans.cruddb.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Added Password Encoder to Encode Password Field
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public Optional<User> createUser(User user){
        try{
            //        Encoding Password & setting it.
            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            Adding User Role
            user.setRoles(Set.of(Role.USER));
            return Optional.of(userRepository.save(user));
        }catch (Exception e){
            log.error("User Not Created");
            return Optional.empty();
        }
    }

    public Optional<User> saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(Role.ADMIN));
        return Optional.of(userRepository.save(user));
    }

    @Transactional
    public User updateUser(User user){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User updtaedUser = findByUserName(userName);
        updtaedUser.setUserName(user.getUserName());
        updtaedUser.setPassword(user.getPassword());
        return userRepository.save(updtaedUser);
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }
}
