package habsida.spring.boot_security.demo.controllers;

import habsida.spring.boot_security.demo.models.User;
import habsida.spring.boot_security.demo.services.RoleService;
import habsida.spring.boot_security.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/gen")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public ModelAndView showUsers(Principal principal) {
        ModelAndView mov = new ModelAndView("/gen");
        mov.addObject("users", userService.listUsers());
        mov.addObject("roles", roleService.listRoles());
        mov.addObject("username", userService.findByEmail(principal.getName()));
        return mov;
    }

    @GetMapping("/admin/new")
    public ModelAndView newPerson(@ModelAttribute("user") User user) {
        ModelAndView mov = new ModelAndView("/gen");
        mov.addObject("roles", roleService.listRoles());
        return mov;
    }

    @PostMapping("/admin/new")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin/gen";
    }

    @GetMapping("/admin/edit")
    public ModelAndView edit(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("/gen");
        User user = userService.userById(id).get();
        mav.addObject("user", user);
        mav.addObject("roles", roleService.listRoles());
        return mav;
    }

    @PostMapping("/admin/edit")
    public String update(@ModelAttribute("user") User user) {
    userService.update(user);
        return "redirect:/admin/gen";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);
        return "redirect:/admin/gen";
    }

    @GetMapping("/user")
    public ModelAndView user(Principal principal) {
        ModelAndView mov = new ModelAndView("/gen");
        mov.addObject("user", userService.findByEmail(principal.getName()));
        return mov;
    }
}
