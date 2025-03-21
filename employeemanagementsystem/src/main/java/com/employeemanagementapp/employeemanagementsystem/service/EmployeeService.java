package com.employeemanagementapp.employeemanagementsystem.service;

import com.employeemanagementapp.employeemanagementsystem.models.Employee;
import com.employeemanagementapp.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getALlEmployee(){
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee By Id: " + id + " Not Found"));
    }

    public Employee updateEmployee(Long id, Employee updateEmployee){
        Employee oldEmployee = getEmployee(id);
        oldEmployee.setName(updateEmployee.getName());
        oldEmployee.setEmail(updateEmployee.getEmail());
        oldEmployee.setDepartment(updateEmployee.getDepartment());
        oldEmployee.setSalary(updateEmployee.getSalary());

        return employeeRepository.save(oldEmployee);
    }

    public void deleteEmployee(Long id){
        Employee deleteEmployee = getEmployee(id);
        employeeRepository.delete(deleteEmployee);
    }
}
