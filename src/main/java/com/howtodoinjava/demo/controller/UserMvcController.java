package com.howtodoinjava.demo.controller;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.User;
import com.howtodoinjava.demo.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/User")
public class UserMvcController
{
    @Autowired
    UserServiceImp service;

    @RequestMapping
    public String getAllUsers(Model model)
    {
        List<User> list = service.getAllUsers();

        model.addAttribute("users", list);
        return "User/list-users";
    }

    @RequestMapping(path = "/edit")
    public String editUserById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", service.findByUsername(name).get());

        return "User/add-edit-user";
    }

    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String createOrUpdateUser(User User)
    {
        service.createOrUpdateUser(User);

        return "redirect:/logout";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteUserById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException
    {
        service.deleteUserById(id);
        return "redirect:/User";
    }


    @RequestMapping(path = "/adminUser/{id}")
    public String RoleUpdateUser(Model model, @PathVariable("id") Long id) throws RecordNotFoundException
    {
        Optional<User> user= service.userRepository.findById(id);

        User us=user.get();

        service.AdminUser(us);

        return "redirect:/User";
    }
}
