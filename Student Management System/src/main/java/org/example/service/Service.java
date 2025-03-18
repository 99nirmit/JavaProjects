package org.example.service;

import org.example.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private static ArrayList<Student> students = new ArrayList<>();

    public static void addStudent(Student newStudent){
        students.add(newStudent);
    }

    public static List<Student> getAllStudents(){
        return students;
    }

    public static Student getStudent(Integer id){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getId().equals(id)){
                return students.get(i);
            }
        }

//        students.stream().filter(it -> it.getId().equals(id))
//                .findFirst()
//                .orElse(null);

        return null;
    }

    public static void updateStudent(Integer id, Student student){
        for (int i = 0; i < students.size(); i++){
            if(students.get(i).getId().equals(id)){
                students.get(i).setName(student.getName());
                students.get(i).setCourse(student.getCourse());
                students.get(i).setAge(student.getAge());
                return;
            }
        }

        students.forEach(std -> {
            if(std.getId().equals(id)){
                std.setName(student.getName());
                std.setCourse(student.getCourse());
                std.setAge(student.getAge());
            }
                });
    }

    public static void deleteStudent(Integer id){
        students.removeIf(student -> student.getId().equals(id));
    }
}
