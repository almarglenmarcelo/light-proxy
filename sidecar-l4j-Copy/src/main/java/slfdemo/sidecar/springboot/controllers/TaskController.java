package slfdemo.sidecar.springboot.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import slfdemo.sidecar.springboot.service.ITaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final ITaskService taskService;

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping
    public ResponseEntity<Object> getAllTasks(HttpServletRequest servletRequest){
        return taskService.getTasks(servletRequest);
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<Object> getTaskById(HttpServletRequest servletRequest, @PathVariable int taskId){
        return taskService.getTaskById(servletRequest, taskId);
    }

    @PostMapping
    public ResponseEntity<Object> createTask(HttpServletRequest servletRequest, @RequestBody HashMap<String, Object> body){
        return taskService.createTask(servletRequest, body);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> deleteTaskById(HttpServletRequest servletRequest, @PathVariable int taskId){
        return taskService.deleteTask(servletRequest, taskId);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Object> updateTaskById(HttpServletRequest servletRequest, @PathVariable int taskId,@RequestBody HashMap<String, Object> data){
        return taskService.updateTaskById(servletRequest, taskId, data);
    }

}
