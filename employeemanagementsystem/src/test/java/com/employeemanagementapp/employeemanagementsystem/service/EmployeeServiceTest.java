package com.employeemanagementapp.employeemanagementsystem.service;

import com.employeemanagementapp.employeemanagementsystem.dataHelper.TestDataHelper;
import com.employeemanagementapp.employeemanagementsystem.models.Employee;
import com.employeemanagementapp.employeemanagementsystem.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {


    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void addEmployee() {

        //Arrange
        Employee expected = TestDataHelper.createSavedEmployee();
        when(employeeRepository.save(ArgumentMatchers.any(Employee.class)))
                .thenReturn(expected);

        //Act
        Employee result = employeeService.addEmployee(TestDataHelper.createNewEmployee());

        //Assert
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getDepartment(), result.getDepartment());
        assertEquals(expected.getSalary(), result.getSalary());
        assertEquals(expected.getEmail(), result.getEmail());

        verify(employeeRepository).save(ArgumentMatchers.any(Employee.class));

    }

    @Test
    void getAllEmployee() {

        //Arrange
        int page = 0;
        int size = 5;
        String sortBy = "name";
        String order = "asc";

        //Create test data
        List<Employee> employeeList = TestDataHelper.createEmployeeList(20);
        Page<Employee> mockPage = new PageImpl<>(
                employeeList.subList(0, size),
                PageRequest.of(page, size, Sort.by(sortBy).ascending()),
                employeeList.size()
        );

        when(employeeRepository.findAll(any(Pageable.class)))
                .thenReturn(mockPage);

        //Act
        Page<Employee> result = employeeService.getAllEmployee(page, size, sortBy, order);

        //Assert
        assertEquals(size, result.getContent().size());
        assertEquals(employeeList.size(), result.getTotalElements());
        assertEquals(page, result.getNumber());
        assertEquals(size, result.getSize());
//        assertTrue(result.getContent().get(0).getName() <= result.getContent().get(1).getName());

        verify(employeeRepository).findAll(any(Pageable.class));
    }

    @Test
    void getEmployee() {

        Employee expected = TestDataHelper.createSavedEmployee();

        //Arrange
        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(expected));

        //Act
        Employee result = employeeService.getEmployee(1L);

        //Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(employeeRepository).findById(1L);
    }

    @Test
    void updateEmployee() {
        //Arrange
        Employee existingEmployee = TestDataHelper.createEmployee(1L, "Nirmit", "Developer", 9876.16, "nirmt@gmail.com");
        Employee updatedEmployee = TestDataHelper.createEmployee(1L, "Uday", "Tester", 93451.91, "uday@gmail.com");

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(existingEmployee));

        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //Act
        Employee result = employeeService.updateEmployee(1L, updatedEmployee);

        //Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Uday", result.getName());
        assertEquals("Tester", result.getDepartment());
        assertEquals(93451.91, result.getSalary());
        assertEquals("uday@gmail.com", result.getEmail());


        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(existingEmployee);
    }

    @Test
    void deleteEmployee() {

        //Arrange
        doNothing().when(employeeRepository).deleteById(1L);

        //Act
        employeeService.deleteEmployee(1L);

        //Assert
        verify(employeeRepository).deleteById(1L);
    }

//    Test method banaya
    @Test
    void getEmployeeByDepartment() {

        //Arrange

        //Input prepare kiya
        String department = "IT";
        int page = 0;
        int size = 10;
        //Pageable bhi banaya taaki usi object se compare kar sakein.
        Pageable pageable = PageRequest.of(page, size);

        //Use your helper to create mock data
        List<Employee> employeeList = TestDataHelper.createEmployeeList(5);

        //PageImpl with mock data
        Page<Employee> mockPage = new PageImpl<>(employeeList, pageable, employeeList.size());

        //Mock behavior define kiya.
        //Matlab: Jab findByDepartment(...) call ho, toh uska jawab mockPage ho.
        //Yeh step bohot important hai, yahan aap batate ho ki mocked function kya return kare.
        when(employeeRepository.findByDepartment(department, pageable))
                .thenReturn(mockPage);

        //Act

        // Actual function call kiya
        //Ab humne apna target method call kiya aur uska result liya.
        Page<Employee> result = employeeService.getEmployeeByDepartment(department, page, size);

        //Assert

        // Result verify kiya
        //Jo mocked result banaya tha (mockPage), wahi expected hai.
        //Agar function galat call karta ya galat pageable banata, toh test fail ho jata.
        assertEquals(mockPage, result, "Page of employee should match");
        assertEquals(4, result.getContent().size());
    }

    @Test
    void uploadFile() {
    }

    @Test
    void getFilesByEmployeeName() {

    }

    @Test
    void downloadFile() {
    }
}