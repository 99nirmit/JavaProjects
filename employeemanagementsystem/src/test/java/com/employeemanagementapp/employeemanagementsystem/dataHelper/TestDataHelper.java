package com.employeemanagementapp.employeemanagementsystem.dataHelper;

import com.employeemanagementapp.employeemanagementsystem.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class TestDataHelper {

    public static Employee createEmployee(Long id, String name, String department, Double salary, String email){
        Employee employee = new Employee();

        employee.setId(id);
        employee.setName(name);
        employee.setDepartment(department);
        employee.setSalary(salary);
        employee.setEmail(email);

        return employee;
    }

    public static List<Employee> createEmployeeList(int count){
        List<Employee> employeeList = new ArrayList<>();
        for(int i = 1; i < count; i++){
            employeeList.add(createEmployee((long) i, "Nirmit", "Developer", 9876.21, "nirmit@gmail.com"));
        }
        return employeeList;
    }

    public static Employee createNewEmployee(){
        return createEmployee(null, "Nirmit", "Developer", 67652.12, "nirmit@gmail.com");
    }

    public static Employee createSavedEmployee() {
        return createEmployee(1L, "Nirmit", "Developer", 67652.12, "nirmit@gmail.com");
    }
}
