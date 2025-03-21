package com.employeemanagementapp.employeemanagementsystem.repository;

import com.employeemanagementapp.employeemanagementsystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment(String department, Pageable pageable);

    List<Employee> findByName(String name);

    List<Employee> findBySalary(Double salary);

}
