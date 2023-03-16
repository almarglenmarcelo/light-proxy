package slfdemo.sidecar.springboot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import slfdemo.sidecar.springboot.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("{userId}")
    public ResponseEntity<Object> retrieveUser(HttpServletRequest servletRequest, @PathVariable int userId){
        return userService.retrieveUser(servletRequest, userId);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(HttpServletRequest servletRequest, @RequestBody HashMap<String, Object> data){
        return userService.createUser(servletRequest, data);
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(HttpServletRequest servletRequest){
        return userService.retrieveAllUsers(servletRequest);
    }

}
