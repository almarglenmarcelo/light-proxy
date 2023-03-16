package slfdemo.sidecar.springboot.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import slfdemo.sidecar.springboot.entity.User;
import slfdemo.sidecar.springboot.repository.IUserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements IUserService{

    private IUserRepository userRepository;

    @Override
    public ResponseEntity<Object> createUser(HttpServletRequest servletRequest, HashMap<String, Object> data) {
        HashMap<String, Object> response = new HashMap<>();

        String username = data.get("username").toString();

        User newUser = new User(username);
        userRepository.save(newUser);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{userId}")
                            .buildAndExpand(newUser.getId())
                            .toUri();


        response.put("result", "USER_CREATED");
        response.put("location", location);
        ResponseEntity.created(location).build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> retrieveUser(HttpServletRequest servletRequest, int userId) {
        HashMap<String, Object> response = new HashMap<>();

        Optional<User> priorUser = userRepository.findById(userId);

        if(priorUser.isEmpty()){
            response.put("result", "USER_NOT_FOUND");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(priorUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> retrieveAllUsers(HttpServletRequest servletRequest) {
        HashMap<String, Object> response = new HashMap<>();

        List<User> allUsers = userRepository.findAll();

        if(allUsers.size() == 0) {
            response.put("result", "NO_USERS_REGISTERED");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
