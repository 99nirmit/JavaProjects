package com.todoapp.todolist.services;

import com.todoapp.todolist.models.Task;
import com.todoapp.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService{

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task Not Found"));
    }

    public Task updateTask(Long id, Task newUpdated){

        Task oldUpdate = getTaskById(id);
        oldUpdate.setTitle(newUpdated.getTitle());
        oldUpdate.setDescription(newUpdated.getDescription());
        oldUpdate.setStatus(newUpdated.getStatus());

        return taskRepository.save(oldUpdate);
    }

    public void deleteTask(Long id){
        Task deleteTask = getTaskById(id);
        taskRepository.deleteById(id);
    }
}
