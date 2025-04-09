package com.todoapp.todolist.services;

import com.todoapp.todolist.datahelper.TaskDataHelper;
import com.todoapp.todolist.models.Status;
import com.todoapp.todolist.models.Task;
import com.todoapp.todolist.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;


    @Test
    void createTask() {
        //Arrange
        Task expected = TaskDataHelper.createSavedSpringTask();
        when(taskRepository.save(ArgumentMatchers.any(Task.class)))
                .thenReturn(expected);

        //Act
        Task result = taskService.createTask(TaskDataHelper.createNewSpringTask());

        //Assert
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getTitle(), result.getTitle());
        assertEquals(expected.getDescription(), result.getDescription());
        assertEquals(expected.getStatus(), result.getStatus());

        verify(taskRepository).save(ArgumentMatchers.any(Task.class));
    }

    @Test
    void getAllTask() {
        //Arrange
        when(taskRepository.findAll())
                .thenReturn(TaskDataHelper.createTaskList(5));

        //Act
        List<Task> result = taskService.getAllTask();

        //Assert
        assertEquals(5, result.size());
    }

    @Test
    void getTaskById() {

        Task expected = TaskDataHelper.createSavedSpringTask();

        // Arrange
        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(expected));

        // Act
        Task result = taskService.getTaskById(1L);

        //Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(taskRepository).findById(1L);
    }

    @Test
    void updateTask() {

        //Arrange
        Task existingTask = TaskDataHelper.createTask(1L, "old title", "old description", Status.PENDING);
        Task updatingTask = TaskDataHelper.createTask(1L, "new title", "new description", Status.PENDING);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //Act
        Task result = taskService.updateTask(1L, updatingTask);

        //Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("new title", result.getTitle());
        assertEquals("new description", result.getDescription());
        assertEquals(Status.PENDING, result.getStatus());

        verify(taskRepository).findById(1L);
        verify(taskRepository).save(existingTask);
    }

    @Test
    void deleteTask() {

        //Arrange
        doNothing().when(taskRepository).deleteById(1L);

        //Act
        taskService.deleteTask(1L);

        //Assert
        verify(taskRepository).deleteById(1L);
    }
}