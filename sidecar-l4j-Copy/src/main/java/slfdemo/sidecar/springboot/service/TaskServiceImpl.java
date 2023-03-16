package slfdemo.sidecar.springboot.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import slfdemo.sidecar.springboot.entity.Task;
import slfdemo.sidecar.springboot.repository.ITaskRepository;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TaskServiceImpl implements ITaskService{


    private final ITaskRepository taskRepository;
    @Override
    public ResponseEntity<Object> createTask(HttpServletRequest servletRequest, HashMap<String, Object> data) {
        HashMap<String, String> response = new HashMap<>();

        String task = data.get("task").toString();
        String description = data.get("description").toString();

        if((task.length() <= 1) || (description.length() <= 5) ){
            response.put("result", "TASK_CREATION_FAILED");

            if(task.length() <= 1)
                response.put("message", "Task name must not be less than or equal to 1");

            if(description.length() <= 5)
                response.put("message", "Description length must be greater than 5 character!");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Task newTask = new Task(task, description);
        taskRepository.save(newTask);

        response.put("result", "TASK_CREATED");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getTasks(HttpServletRequest servletRequest) {
        HashMap<String, Object> response = new HashMap<>();

        List<Task> taskLists = taskRepository.findAll();

        if(taskLists.size() == 0){
            response.put("result", "NO_TASKS_FOUND");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

//        response.put("result", "TASKS_FOUND");
        return new ResponseEntity<>(taskLists, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getTaskById(HttpServletRequest servletRequest, int taskId) {
        HashMap<String, Object> response = new HashMap<>();
        Optional<Task> task = taskRepository.findById(taskId);

        if(task.isPresent()) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        }else{
            response.put("result", "TASK_DOES_NOT_EXIT");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> deleteTask(HttpServletRequest servletRequest, int taskId) {
        HashMap<String, Object> response = new HashMap<>();
        Optional<Task> task = taskRepository.findById(taskId);

        if(task.isPresent()) {
            taskRepository.deleteById(taskId);
            response.put("result", "TASK_DELETED");
            response.put("message", String.format("Task '%s' has been deleted with an id of '%s'.", task.get().getTask(), task.get().getId()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("result", "TASK_DOES_NOT_EXIT");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> updateTaskById(HttpServletRequest servletRequest, int taskId, HashMap<String, Object> data) {
        HashMap<String, Object> response = new HashMap<>();
        Optional<Task> task = taskRepository.findById(taskId);

        if(task.isEmpty()) {
            response.put("result", "TASK_NOT_FOUND");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        String newTaskName = data.get("task").toString();
        String newDescription = data.get("description").toString();

        taskRepository.save(new Task(task.get().getId(), newTaskName, newDescription));
        response.put("result", "TASK_UPDATED");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
