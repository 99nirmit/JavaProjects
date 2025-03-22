package com.employeemanagementapp.employeemanagementsystem.service;

import com.employeemanagementapp.employeemanagementsystem.models.Employee;
import com.employeemanagementapp.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Page<Employee> getAllEmployee(int page, int size, String sortBy, String order){
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageable);
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

    public Page<Employee> getEmployeeByDepartment(String department, int page, int size){
        Pageable pageable1 = PageRequest.of(page, size);
        return employeeRepository.findByDepartment(department, pageable1);
    }
}
