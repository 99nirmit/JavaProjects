    package com.todoapp.todolist.datahelper;
    import com.todoapp.todolist.models.Status;
    import com.todoapp.todolist.models.Task;

    import java.util.ArrayList;
    import java.util.List;

    public class TaskDataHelper {

        public static Task createTask(Long id, String title, String description, Status status){
            Task task = new Task();

            task.setId(id);
            task.setTitle(title);
            task.setDescription(description);
            task.setStatus(status);

            return task;
        }

        public static List<Task> createTaskList(int count){
            List<Task> tasks = new ArrayList<>();
            for(int i = 0; i < count; i++){
                tasks.add(createTask((long) i, "Java", "Java Programs", Status.PENDING));
            }
            return tasks;
        }

        public static Task createNewSpringTask() {
            return createTask(null, "Spring", "Spring Program", Status.PENDING);
        }

        public static Task createSavedSpringTask() {
            return createTask(1L, "Spring", "Spring Program", Status.PENDING);
        }
    }
