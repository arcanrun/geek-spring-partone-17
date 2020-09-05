package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlCall;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persistance.User;
import ru.geekbrains.persistance.UserRepository;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String allUsers(Model model) throws SQLException {
        List<User> allUsers = userRepository.getAllUsers();
        model.addAttribute("users", allUsers);
        return "users";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) throws SQLException {
        User user = userRepository.findById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/update")
    public String updateUser(User user) throws SQLException {
        userRepository.update(user);
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) throws SQLException {
        boolean res = userRepository.deleteById(id);
        if (res) {
            System.out.printf("User with id: %s has been deleted\n", id);
        } else {
            System.out.printf("User with id: %s is not deleted\n", id);
        }
        return "redirect:/user";
    }

    @GetMapping("/add")
    public String addUser(Model model) throws SQLException {
        List<User> usersList = userRepository.getAllUsers();
        int id = 0;
        if (usersList.size() > 0) {
            id = usersList.get(usersList.size() - 1).getId();
        }
        model.addAttribute("user", new User(id, "", ""));
        return "addUser";
    }

    @PostMapping("/add")
    public String addUser(User user) throws SQLException {
        userRepository.insert(user);
        return "redirect:/user";
    }

}
