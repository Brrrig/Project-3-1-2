package habsida.spring.boot_security.demo.controllers;


import habsida.spring.boot_security.demo.models.User;
import habsida.spring.boot_security.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rest/admin/gen")
public class UserRestController {

    private final UserService userService;

    private ResponseEntity<List<User>> responseEntitySuc() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping()
    public ResponseEntity<List<User>> showUsers() {
        return responseEntitySuc();
    }

    @PostMapping()
    public ResponseEntity<List<User>> create(@RequestBody User user) {
        userService.add(user);
        return responseEntitySuc();
    }

    @PutMapping()
    public ResponseEntity<List<User>> update(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword(userService.userById(user.getId()).get().getPassword());
        }
        if (user.getRoles().isEmpty()) {
            user.setRoles(userService.userById(user.getId()).get().getRoles());
        }
        userService.update(user);

        return responseEntitySuc();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable("id") long id) {
        userService.remove(id);
        return responseEntitySuc();
    }
}
