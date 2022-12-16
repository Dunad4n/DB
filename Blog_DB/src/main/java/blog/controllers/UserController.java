package blog.controllers;

import blog.entities.User;
import blog.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:8081/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") User user) {
        return user;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") User userFromDB, @RequestBody User user) {
        BeanUtils.copyProperties(user, userFromDB, "id", "date");

        return userRepository.save(userFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userRepository.delete(user);
    }

}
