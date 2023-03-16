package slfdemo.sidecar.springboot.service;

import org.springframework.http.ResponseEntity;
import slfdemo.sidecar.springboot.entity.Task;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface ITaskService {

    ResponseEntity<Object> createTask(HttpServletRequest servletRequest, HashMap<String, Object> data);
    ResponseEntity<Object> getTasks(HttpServletRequest servletRequest);
    ResponseEntity<Object> getTaskById(HttpServletRequest servletRequest, int taskId);
    ResponseEntity<Object> deleteTask(HttpServletRequest servletRequest, int taskId);
    ResponseEntity<Object> updateTaskById(HttpServletRequest servletRequest, int taskId, HashMap<String, Object> data);

}
