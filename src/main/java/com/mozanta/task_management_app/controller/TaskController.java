package com.mozanta.task_management_app.controller;

import com.mozanta.task_management_app.model.Task;
import com.mozanta.task_management_app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

//    Api for adding a new task
    @PostMapping("/task/add")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        try {
            Task _task = taskRepository.save(new Task(task.getTask(),false));
            return new ResponseEntity<>(_task, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    Api for retrieving all tasks
    @GetMapping("/tasks")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false) String task) {
        try {
            List<Task> tasks = new ArrayList<Task>();

            if (task == null)
                taskRepository.findAll().forEach(tasks::add);
            else
                taskRepository.findByTaskContaining(task).forEach(tasks::add);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //    Api for marking task as complete or not complete
    @PutMapping("/task/complete/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Task> updateTask(@PathVariable("id") String id, @RequestBody Task task) {
        Optional<Task> taskData = taskRepository.findById(id);

        if (taskData.isPresent()) {
            Task _task = taskData.get();
            _task.setCompleted(task.isCompleted());
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    Api for deleting a task
    @DeleteMapping("/task/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") String id) {
        try {
            taskRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}