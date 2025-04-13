package com.ecommerceapp.ecommerce.dataHelper;

import com.ecommerceapp.ecommerce.Enum.Role;
import com.ecommerceapp.ecommerce.Enum.Status;
import com.ecommerceapp.ecommerce.models.Cart;
import com.ecommerceapp.ecommerce.models.Order;
import com.ecommerceapp.ecommerce.models.User;
import com.ecommerceapp.ecommerce.services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class UserDataHelper {

    public static User createUser(Long id, String name, String email, String password, String phoneNumber, Role role, String address, Status status, Cart cart, List<Order> orders){
        User user = new User();
        user.setId(1L);
        user.setName("Nirmit");
        user.setEmail("nirmit@gmail.com");
        user.setPassword("1234");
        user.setRoles(role);
        user.setAddress("Add");
        user.setStatus(Status.ACTIVE);
        user.getCart();
        user.setOrders(new ArrayList<>());

        return user;
    }

    public static List<User> createUsersList(int count){
        List<User> userList = new ArrayList<>();

        for(int i = 0; i < count; i++){
            userList.add(createUser((long) 1, "Nirmit", "nirmit@gmail.com", "1234", "9876551", Role.USER,"Add", Status.ACTIVE, new Cart(), new ArrayList<>()));
        }
        return userList;
    }

    public static User createNewUsers(){
        return createUser(null,"Nirmit", "nirmit@gmail.com", "1234", "9876551", Role.USER,"Add", Status.ACTIVE, new Cart(), new ArrayList<>());
    }

    public static User createSavedUsers(){
        return createUser(1L,"Nirmit", "nirmit@gmail.com", "1234", "9876551", Role.USER,"Add", Status.ACTIVE, new Cart(), new ArrayList<>());
    }
}
