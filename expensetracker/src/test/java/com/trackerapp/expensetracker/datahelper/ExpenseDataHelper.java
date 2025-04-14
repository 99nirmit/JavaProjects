package com.trackerapp.expensetracker.datahelper;

import com.trackerapp.expensetracker.models.Expense;
import com.trackerapp.expensetracker.models.User;
import org.springframework.beans.factory.parsing.EmptyReaderEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseDataHelper {

    public static Expense createExpense(Long id, Double amount, User user, String category, String description, Date date){

        Expense expense = new Expense();

        expense.setId(id);
        expense.setAmount(amount);
        expense.setUser(new User());
        expense.setCategory(category);
        expense.setDescription(description);
        expense.setDate(date);

        return expense;
    }

    public static List<Expense> createExpenseList(int count){
        List<Expense> expenseList = new ArrayList<>();

        for(int i = 0; i < count; i++){
            expenseList.add(createExpense((long) i, 765431.12, new User(), "Food", "Junk Foods", new Date()));
        }
        return expenseList;
    }

    public static Expense createNewExpense(){
        return createExpense(null, 8765.09, new User(), "Foods", "Junk Foods", new Date());
    }

    public static Expense createSavedExpense(){
        return createExpense(1L, 8765.09, new User(), "Foods", "Junk Foods", new Date());
    }
}
