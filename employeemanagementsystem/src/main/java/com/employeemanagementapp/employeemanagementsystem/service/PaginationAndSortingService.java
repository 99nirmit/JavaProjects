package com.employeemanagementapp.employeemanagementsystem.service;

import com.employeemanagementapp.employeemanagementsystem.models.Employee;
import com.employeemanagementapp.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaginationAndSortingService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> sortByDepartment(String department, int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy.split(",")[0])
                .ascending());

        if(sortBy.split())

    }


}
