package com.employeemanagementapp.employeemanagementsystem.controller;

import com.employeemanagementapp.employeemanagementsystem.models.Employee;
import com.employeemanagementapp.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    private Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/")
    private Page<Employee> getAllEmployee(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy,
                                          @RequestParam(defaultValue = "asc") String order) {
        return employeeService.getAllEmployee(page, size, sortBy, order);
    }

    @GetMapping("/{id}")
    private Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @PutMapping("/update/{id}")
    private Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updateEmployee) {
        return employeeService.updateEmployee(id, updateEmployee);
    }

    @DeleteMapping("/delete/{id}")
    private void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/department")
    private Page<Employee> getEmployeeByDepartment(@RequestParam String department,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        return employeeService.getEmployeeByDepartment(department, page, size);
    }

    @PostMapping("/{id}/upload")
    private ResponseEntity<String> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is Empty");
        }
        return employeeService.uploadFile(id, file);
    }

    @GetMapping("/{id}/files")
    private ResponseEntity<List<String>> getFilesByEmployeeId(@PathVariable Long id){
        return employeeService.getFilesByEmployeeName(id);
    }

    @GetMapping("/{id}/download/{filename}")
    private ResponseEntity<Resource> downloadFile(@PathVariable Integer id, @PathVariable String filename){
        return employeeService.downloadFile(id, filename);
    }
}
