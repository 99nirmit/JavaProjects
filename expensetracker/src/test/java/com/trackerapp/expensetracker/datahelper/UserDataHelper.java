package com.trackerapp.expensetracker.datahelper;

import com.trackerapp.expensetracker.models.Expense;
import com.trackerapp.expensetracker.models.Role;
import com.trackerapp.expensetracker.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataHelper {

    public static User createUser(Long id, String name, String email, String password, String mobile, List<Expense> expenseList, Role role){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setMobile(mobile);
        user.setExpenses(expenseList);
        user.setRole(role);

        return user;
    }

    public static List<User> createUserLists(int count){
        List<User> usersList = new ArrayList<>();
        for(int i = 0; i < count; i++){
            usersList.add(createUser((long) i, "Nirmit", "email", "password", "mobile", new ArrayList<>(), Role.USER));
        }
        return usersList;
    }

    public static User createNewUser(){
        return createUser(null, "Nirmit", "email", "password", "mobile", new ArrayList<>(), Role.USER);
    }

    public static User createSavedUser(){
        return createUser(1L, "Nirmit", "email", "password", "mobile", new ArrayList<>(), Role.USER);
    }
}
