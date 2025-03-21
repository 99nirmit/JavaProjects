package com.employeemanagementapp.employeemanagementsystem.controller;

import com.employeemanagementapp.employeemanagementsystem.models.Employee;
import com.employeemanagementapp.employeemanagementsystem.service.EmployeeService;
import com.employeemanagementapp.employeemanagementsystem.service.PaginationAndSortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PaginationAndSortingService paginationAndSortingService;

    @PostMapping("/save")
    private Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/")
    private List<Employee> getAllEmployee(){
        return employeeService.getALlEmployee();
    }

    @GetMapping("/{id}")
    private Employee getEmployee(@PathVariable Long id){
        return employeeService.getEmployee(id);
    }

    @PutMapping("/update/{id}")
    private Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updateEmployee){
        return employeeService.updateEmployee(id, updateEmployee);
    }

    @DeleteMapping("/delete/{id}")
    private void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/department")
    private List<Employee> getEmployeeByDepartment(@RequestParam String department){
        return paginationAndSortingService.sortByDepartment(department);
    }

    @GetMapping("/salary")


}
