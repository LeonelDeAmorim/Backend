package application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.exception.UserNotFoundException;
import application.model.User;
import application.repository.UserRepository;

@RestController
public class UserController {
 
private final UserRepository repository;

UserController(UserRepository repository) {
this.repository = repository;
}

@GetMapping("/users")
List<User> all() {
return repository.findAll();
}

@PostMapping("/users")
User newUser(@RequestBody User newUser) {
return repository.save(newUser);
}

@GetMapping("/users/{id}")
User one(@PathVariable Long id) {

return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
}

@PutMapping("/employees/{id}")
User replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {

return repository.findById(id).map(employee -> {
employee.setUsername(newUser.getUsername());
employee.setPassword(newUser.getPassword());
return repository.save(employee);
}).orElseGet(() -> {
return repository.save(newUser);
});
}

@DeleteMapping("/users/{id}")
void deleteEmployee(@PathVariable Long id) {
repository.deleteById(id);
}

  @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody User loginUser) {

        Optional<User> userOptional = repository.findByUsername(loginUser.getUsername());
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(loginUser.getPassword())) {

            return ResponseEntity.ok("Authentication successful");
         
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Invalid username or password");
        }
    }

}