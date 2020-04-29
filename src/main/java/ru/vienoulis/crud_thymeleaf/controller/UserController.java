package ru.vienoulis.crud_thymeleaf.controller;


import ru.vienoulis.crud_thymeleaf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vienoulis.crud_thymeleaf.service.interfaces.RoleService;
import ru.vienoulis.crud_thymeleaf.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/**")
    public String getAll() {
        return "login";
    }

    @GetMapping(value = "user")
    public String getUser(ModelMap map, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        map.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "login")
    public String loginPage(ModelMap map) {
        return "login";
    }
    @GetMapping("/admin/user")
    public String getUser(ModelMap map) {
        List<User> users = userService.getUsers();
        map.addAttribute("users", users);
        map.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @PostMapping("/admin/user")
    public String postUser(ModelMap map, User user, String... roleList) {
        user.setRoleSet(
                Arrays.stream(roleList)
                        .map(x -> roleService.getRoleByName(x))
                        .collect(Collectors.toSet())
        );
        userService.add(user);
        List<User> users = userService.getUsers();
        map.addAttribute("users", users);
        map.addAttribute("roles", roleService.getAllRoles());

        return "admin";
    }

    @PostMapping("/admin/delete")
    public String postDelete(String userId, ModelMap map) {
        userService.delete(userId);
        map.addAttribute("users", userService.getUsers());

        return "redirect:/admin/user";
    }
    @GetMapping("/admin/update")
    public String getUpdate(Long userId, ModelMap map) {
        User user = userService.getUserById(userId);
        map.addAttribute("userUpdated", user);
        map.addAttribute("roles", roleService.getAllRoles());
        return "update";
    }

    @PostMapping("/admin/update")
    public String postUpdate( User user, String... roleList) {

        user.setRoleSet(Arrays.stream(roleList)
                .map(x->roleService.getRoleByName(x))
                .collect(Collectors.toSet())
        );
        userService.updateUser(user);

        return "redirect:/admin/user";
    }
}