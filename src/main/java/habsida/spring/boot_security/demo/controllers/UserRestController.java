package habsida.spring.boot_security.demo.controllers;


import habsida.spring.boot_security.demo.models.User;
import habsida.spring.boot_security.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/gen")
public class UserRestController {

    private final UserService userService;

    private ResponseEntity<List<User>> rok() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping()
    public ResponseEntity<List<User>> showUsers() {
        return rok();
    }

    @PostMapping()
    public ResponseEntity<List<User>> create(@RequestBody User user) {
        userService.add(user);
        return rok();
    }

    @PutMapping()
    public ResponseEntity<List<User>> update(@RequestBody User user) {
        userService.update(user);
        return rok();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);
        return rok();
    }
}
