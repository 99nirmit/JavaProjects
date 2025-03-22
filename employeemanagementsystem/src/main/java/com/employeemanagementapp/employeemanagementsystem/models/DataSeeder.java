package com.employeemanagementapp.employeemanagementsystem.models;

import com.employeemanagementapp.employeemanagementsystem.repository.EmployeeRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataSeeder implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final Faker faker = new Faker();
    private Random random = new Random();

    public DataSeeder(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (employeeRepository.count() == 0) {
            for(int i = 0; i < 50; i++){
                Employee employee = new Employee();
                employee.setName(faker.name().name());
                employee.setDepartment(getRandomDepartment());
                employee.setEmail(faker.internet().emailAddress());
                employee.setSalary(getRandomSalary());

                employeeRepository.save(employee);
            }
        }
    }

    private String getRandomDepartment() {
        String[] departments = {"IT", "FINANCE", "MARKETING", "SALES", "HR", "CUSTOMER SERVICE", "R&D", "OPERATIONS MANAGEMENT", "ACCOUNTING", "ADMINISTRATION"};
        return departments[random.nextInt(departments.length)];
    }

    private double getRandomSalary(){
        return 100000 + (random.nextDouble() * 700);
    }
}
