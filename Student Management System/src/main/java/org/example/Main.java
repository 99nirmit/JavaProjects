package org.example;

import org.example.models.Student;
import org.example.service.Service;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Service service;

    public static void main(String[] args) {

//        read input from the user
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.println("\nWelcome to Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Student");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("Choose an Option: ");
//            reads an integer entered by the use
            choice = scanner.nextInt();

            switch(choice) {
                case 1 -> addStudent(scanner);

                case 2 -> getAllStudent();

                case 3 -> getStudent(scanner);

                case 4 -> updateStudent(scanner);

                case 5 -> deleteStudent(scanner);

                default -> System.out.println("Invalid Choice! Please try again.");
            }
        } while (choice != 6);
    }

    private static void addStudent(Scanner scanner){
        System.out.println("Enter Student Id: ");
        Integer id = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Student Course: ");
        String courseName = scanner.nextLine();

        System.out.println("Enter Student age: ");
        Integer age = scanner.nextInt();

        Student newStudent = new Student(id, name, courseName, age);
        service.addStudent(newStudent);

        System.out.println("Student Added Successfully");
    }

    private static void getAllStudent(){
        List<Student> getAllStudents = service.getAllStudents();
        System.out.println(getAllStudents);
    }

    private static void getStudent(Scanner scanner){
        System.out.println("Enter Student Id:- ");
        Integer id = scanner.nextInt();
        Student student = service.getStudent(id);
        System.out.println(student);
    }

    private static void updateStudent(Scanner scanner){
        System.out.println("Enter Student Id:- ");
        Integer id = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Enter Student Name:- ");
        String name = scanner.nextLine();

        System.out.println("Enter Student Course Name:- ");
        String courseName = scanner.nextLine();

        System.out.println("Enter Student Age:- ");
        Integer age = scanner.nextInt();

        Student student = new Student(id, name, courseName, age);
        service.updateStudent(id, student);

        System.out.println("Student Details Updated Successfully");
    }

    private static void deleteStudent(Scanner scanner){
        System.out.println("Enter Student id:- ");
        Integer id = scanner.nextInt();

        service.deleteStudent(id);
        System.out.println("Student Details Delete");
    }
}