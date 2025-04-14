package com.todoapp.todolist.services;

import com.todoapp.todolist.models.Status;
import com.todoapp.todolist.repository.TaskRepository;
import com.todoapp.todolist.models.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TaskSchedulerService {

    private final TaskRepository taskRepository;

    public TaskSchedulerService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void markOverdueTasks(){
        List<Task> getAllTasks = taskRepository.findAll();
        for(Task task : getAllTasks){
            if(task.getDueDate().isBefore(LocalDateTime.now()) && task.getStatus() == Status.PENDING) {
                task.setStatus(Status.IN_PROGRESS);
                taskRepository.save(task);
            }
        }

        System.out.println("Checked for overdue tasks.");
    }
}
