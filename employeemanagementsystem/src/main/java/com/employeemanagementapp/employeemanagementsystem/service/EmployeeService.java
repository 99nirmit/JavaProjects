package com.employeemanagementapp.employeemanagementsystem.service;

import com.employeemanagementapp.employeemanagementsystem.models.Employee;
import com.employeemanagementapp.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    private static final String UPLOAD_DIR = "E:/Files/";

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

    public ResponseEntity<String> uploadFile(Long id, MultipartFile file){

        try{
            File uploadDir = new File(UPLOAD_DIR);
            if(uploadDir.exists()){
                uploadDir.mkdirs();
            }
            File destinationFile =  new File(UPLOAD_DIR + id + "_" + file.getOriginalFilename());
            file.transferTo(destinationFile);

            return ResponseEntity.ok("File Upload Successfully: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File Upload failed: " + e.getMessage());
        }
    }

    public ResponseEntity<List<String>> getFilesByEmployeeName(Long id){
        File uploadDir = new File(UPLOAD_DIR);

        if(!uploadDir.exists() || !uploadDir.isDirectory()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonList("No Files Found"));
        }

        File[] files = uploadDir.listFiles(((dir, name) -> name.startsWith(id + "_")));

        if(files == null || files.length == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonList("No files found for employee ID:" + id));
        }

        List<String> filesNames = Arrays.stream(files)
                .map(File::getName)
                .collect(Collectors.toList());

        return ResponseEntity.ok(filesNames);
    }

    public ResponseEntity<Resource> downloadFile(Integer id, String filename){
        try{
            Path filePath = Paths.get("Files").resolve(filename).normalize();

            if(!Files.exists(filePath)){
                throw new RuntimeException("File Not found: " + filename);
            }
            Resource resource = new UrlResource(filePath.toUri());

            if(!resource.exists() || !resource.isReadable()){
                throw new RuntimeException("File is not readable: " + filename);
            }


                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\"")
                        .body(resource);

            }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
