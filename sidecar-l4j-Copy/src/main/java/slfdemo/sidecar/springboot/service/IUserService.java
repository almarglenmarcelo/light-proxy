package slfdemo.sidecar.springboot.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IUserService {

    ResponseEntity<Object> createUser(HttpServletRequest servletRequest, HashMap<String, Object> data);
    ResponseEntity<Object> retrieveUser(HttpServletRequest servletRequest, int userId);
    ResponseEntity<Object> retrieveAllUsers(HttpServletRequest servletRequest);

}
