package habsida.spring.boot_security.demo.controllers;

import habsida.spring.boot_security.demo.models.User;
import habsida.spring.boot_security.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class GetOneRestController {

    private final UserService userService;

    @GetMapping("/rest/user")
    public ResponseEntity<User> userById(Principal principal) {
        return ResponseEntity.ok(userService.findByEmail(principal.getName()).get());
    }
}
